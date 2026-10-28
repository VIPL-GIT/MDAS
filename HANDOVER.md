# MDMS — Handover Document

_Prepared 2026-10-28 from a read-only review of commit `4972fa4` ("Initial commit - MDMS Java Web Application"), tagged `baseline-2026-10-28`. This review changed no code._

> **Secrets note:** this document says *where* credentials are hard-coded but never repeats them. Treat every credential below as **compromised**: it is in Git history and on GitHub, and the repository is **public**.

---

## 1. Git baseline

| Item | Value |
|---|---|
| Remote | `https://github.com/VIPL-GIT/MDAS` (public) |
| Baseline commit | `4972fa4` — the only commit; matches the source as received |
| Baseline tag | `baseline-2026-10-28` (annotated, created locally) |
| Tracked files | 3,067 (≈116 MB `WebContent`, 3 MB `src`) |
| Working branch | `docs/handover` (holds this file only) |

The `.gitignore` excludes `*.sql`, `*.class`, `*.war`, `build/` and `.env`, so **no database schema or dump is in the repository**.

---

## 2. What the project is

**MDMS (Meter Data Management System)** is the web front end and reporting layer for a DLMS/AMI smart-metering deployment. Internally it is called **RTDAS** (package `com.rtdas`) and deploys as the **MDMS** web app (`display-name` is `MDM`). Configuration strings point to several utility deployments, such as `mppokvvcl` (MP Poorv Kshetra VVCL, Jabalpur), CESC, Avon and "pominda".

Users log in and can:

- browse the **network hierarchy**: Zone → Circle → Division → Sub-division → Section → Substation → Feeder → DTR → Meter/Consumer;
- view **instantaneous** values, **billing** (monthly reset) data, **load survey / load profile**, **events and tampers**, **daily readings**, alerts and modem diagnosis;
- use **dashboards** (amCharts and Google Maps), an **SLD** (single-line diagram) view, **feeder reports**, power-failure/outage reports and energy accounting;
- run **VEE** (Validation, Estimation, Editing) rule summaries;
- send **on-demand read** requests to meters (through a DB request table);
- manage **prepaid** recharges and slabs, and **billing integration** with an external billing web service;
- maintain **master data**: users, roles, devices, makes, modems, data loggers, IEDs, HT/PSL consumers;
- download raw meter files and export reports to Excel (Apache POI).

### Technology

| Area | Technology |
|---|---|
| Language | **Java 8** (Eclipse compliance 1.8), JSP + JSTL, jQuery/JS |
| Web framework | **Spring MVC 3.0.1** (2010, XML config), URLs end in `*.epx` |
| ORM | **Hibernate 3.2.6** annotations + `AnnotationSessionFactoryBean`; most queries are raw SQL/HQL strings |
| Also | Plain JDBC (`utils.DBUtil`) in about 75 JSPs and legacy classes; leftover Struts 1 classes (`ActionBeans`) that no longer compile |
| Database | **MySQL** (Connector/J **5.0.5**), schema `mppokvvcl` (also `mdms` and `rec9` in old configs) |
| Server | **Apache Tomcat 8.0** (Eclipse WTP runtime), Servlet 3.0 |
| Build | **No Maven/Gradle/Ant.** Eclipse "Dynamic Web Project"; all ~95 jars committed in `WebContent/WEB-INF/lib` |
| Other | SimpleCaptcha (login), Apache POI (Excel), Jackson/Gson/json-lib/json-simple/org.json (five JSON libraries), JavaMail, HTTP SMS gateways |

### Code layout

```
src/
  com/rtdas/           ← the "real" Spring application (component-scanned)
    common/            login, users, roles, hierarchy, master-data CRUD (controller/service/dao/models)
    mdm/               meter-data entities: d2_curr, d3*, d4*, d5*, d6*, hierarchy, meter, etc.
    meterdata/         MeterData, Downloads, OnDemand, billing upload, BillingIntegrationDao
    dashboard/         dashboards (DashBoard, Dashboard1, Dashboard_pominda, Indl, Gmap) + DAOs
    reports/           FeederReportsController/Dao (≈5,700 lines together), DashboardDAO, TotalMasterDAO
    feeder_reports/, vee/, hes/, prepaid/  further modules
    interceptors/      SessionInterceptor (DISABLED in dispatcher-servlet.xml)
  utils/               DBUtil (raw JDBC), D2_DAO, graphs, ProcessUploadedBilling (Excel import)
  ActionBeans/ DataBeans/ DAO/   legacy pre-Spring (Struts era) code, partly dead
  prepaid/ smartmeter/ iec104/   JDBC DAOs used directly from JSPs
  resources/           database.properties, SendMail, SendSMS, EncryptPassword, sock (TCP client), captcha
  Test/                scratch classes (matrix examples etc.)
WebContent/
  WEB-INF/web.xml, dispatcher-servlet.xml, views/ (278 JSPs rendered by controllers)
  *.jsp, MasterData/, ondemand/, prepaid/, dashboard/ … (135 JSPs reachable directly by URL, many with SQL inside)
  assets/ js/ css/ images/ gmap/   front-end libraries (amCharts, jQuery, etc.)
  WEB-INF/lib/          all dependencies, incl. MeterReading_48_v4_final.jar (VIPL DLMS reader)
```

About 57,700 lines of Java in 386 files, plus 413 JSPs.

### How it is built and run today (reconstructed)

1. Import into **Eclipse IDE for Enterprise Java** as an existing project (`.project`, `.classpath`, `.settings` are committed).
2. Add an **Apache Tomcat 8.0** server runtime and a **Java 8** JDK.
3. Edit `src/resources/database.properties` for the target MySQL (URL/user/password, `rawdata_path`, SMS/mail flags).
4. Edit the `web.xml` context-params for the upload folders (`D:\Autofiles\…`; these are Windows paths).
5. Run on server, or **Export → WAR** and drop it into Tomcat `webapps/`. The context root is `/MDMS`, and the welcome page is `login.epx`.

**Important: the source as committed does not compile cleanly.** A `javac` run with the bundled jars (JDK 21) gave **89 errors in 15 files**:

- **Missing code/jars that are not in the repo:** `com.rtdas.frtuconfig.beans` (used by `DAO/sldDao`), `FormBeans`, `in.verticross.db`, Struts 1 (`org.apache.struts.action`), `javax.comm` (serial port), `com.vipl.mdm.*` (used by `ConsumerDaoImpl`).
- **JDK-8/Oracle-only internals:** `sun.misc.BASE64Encoder` (used by **password hashing**), `jdk.management.resource.internal`, `oracle.jrockit.jfr`, `javafx`, `javax.annotation`.

Eclipse still writes class files for broken sources, so the deployed WAR was probably built that way, with the broken classes failing only at runtime. **The production WAR may contain classes that are not in this repository.** Before any change, get the deployed WAR from the production server and compare it with this repo.

---

## 3. Data flow: meter → files → database → reports

### Key finding

**This repository contains no code that reads the DLMS converter's CDF/MIOS XML or JSON files and loads them into the database.** There is no XML parser (DOM/SAX/StAX/JAXB) anywhere in `src`, and no `INSERT`/`UPDATE` into the meter-data tables (`d2`, `d3*`, `d4*`, `d5*`, `d6*`, `ls_data_stbl`, `smartmeter_instant`, `events`, …). The web app only **reads** those tables.

The loading step must therefore happen in a **separate program**: the DLMS converter itself, or another "parser/loader" service, plus possibly MySQL stored procedures or events for the `analytics_*` tables. **Find and secure the source of that program as a priority.** The bundled `MeterReading_48_v4_final.jar` (VIPL, main class `com.vipl.main.MeterWriting_GPRS_Avon`, built in 2017 with JDK 1.8.0_151) is a DLMS reader/writer library that the on-demand JSP imports. It is probably related to the converter, but it is a compiled jar, not source.

### End-to-end flow (as far as it can be seen from this side)

```
 ┌─────────────┐  DLMS/GPRS/RF   ┌──────────────────────────┐   CDF / MIOS XML, JSON
 │ Smart meters│ ──────────────▶ │ DLMS converter / HES     │ ─────────────────────────┐
 └─────────────┘                 │ (separate program)       │   instant, billing,      │
        ▲                        └──────────┬───────────────┘   load profile, events,  │
        │ on-demand read                    │ polls smart_meter_od   daily profile     │
        │                                   │                                          ▼
        │                        ┌──────────▼───────────────┐            ┌──────────────────────┐
        └────────────────────────│ MySQL  (mppokvvcl)       │◀───────────│ Loader (NOT IN REPO) │
                                 │  d2 / d2_curr  instant   │   INSERT   │ parses XML/JSON      │
                                 │  d3, d3_b3, d3_b5 billing│            └──────────────────────┘
                                 │  d4_data, d4_data_curr,  │
                                 │  ls_data_stbl  load prof.│◀── stored procs / jobs? (NOT IN REPO)
                                 │  d5_data, events, events_│      analytics_meter_daily_*,
                                 │  summary       events    │      feeder_*_live_status,
                                 │  d6_data      transactions│     power_fail_minutes, vee_summary_daily
                                 │  dN_columns_header (labels)│
                                 │  meter, hierarchy, feeder,│
                                 │  dtr, total_masterdata …  │
                                 └──────────┬───────────────┘
                                            │ SELECT (Hibernate HQL/SQL + raw JDBC)
                ┌───────────────────────────┼─────────────────────────────────────┐
                ▼                           ▼                                     ▼
   Spring controllers (*.epx)      JSPs with inline SQL               Exports
   com.rtdas.*Controller           (getInstantData.jsp,               Excel (POI), CSV,
   → DAO → views/*.jsp             getLoadsurvayData.jsp …)           raw file download
                │                           │
                └────────────► Browser: dashboards, reports, graphs (amCharts, Google Maps)
```

### Mapping of converter outputs to tables

The names follow the Indian **IS 15959 CDF** convention: D1 = general, D2 = instantaneous, D3 = billing, D4 = load survey, D5 = events, D6 = transactions. Rows are keyed by `cdfid` (one per received file).

| Converter output | Main tables the app reads | Main readers in this repo |
|---|---|---|
| Instant | `d2`, `d2_curr`, `smartmeter_instant`, `d2` latest via `max(cdfid)` | `utils/D2_DAO`, `getInstantData.jsp`, `getAllInstant*.jsp`, `smartmeter_instant.jsp`, `MeterDataDaoImpl` |
| Billing | `d3`, `d3_b3`, `d3_b5`, `d3_columns_header`, `auto_reset_energies*`, `billing_summary` | `AutoResetEnergies*`, `Auto_reset_bean`, `AllResetBillings.jsp`, `MeterDataController` |
| Load profile | `d4_data`, `d4_data_curr`, `ls_data_stbl`, `lpdata`, `analyitics_houly_loadprofile_kwh` | `getLoadsurvayData.jsp`, `getLoadSurveyScreen.jsp`, `FeederReportsDao`, `DownloadsDao` |
| Events | `d5_data`, `d5_data_all`, `events`, `events_summary`, `alerts` | `getEventsData.jsp`, `tamperevents.jsp`, `criticaltampers*.jsp`, `AlertsAndDiagnosisDao` |
| Daily profile | `analytics_meter_daily_readings`, `analytics_meter_daily_consumption`, `analytics_meter_daily_demands`, `daysummary` | dashboards, `FeederReportsDao`, `DashboardDaoMP` |
| Communication status | `meter_comm_track`, `meter_comm_track_stats`, `device_summary`, `device_diagnosis_summary` | dashboards, `GenStatus` |

### Data that does enter through this application

| Path | What it does |
|---|---|
| `POST /uploadBillData.epx`, `/processBillData.epx` | Excel billing sheet → `ProcessUploadedBilling` → `INSERT` into `auto_reset_energies_cesc` |
| `POST /proceed.epx` (Downloads) | Saves an uploaded `.emd`/`.gxt`/MIOS file into `D:\Autofiles\EMD\|GXT\|MIOS`. **It does not parse it**; presumably the external loader watches these folders |
| `ondemand/initiateOD.jsp` | `INSERT INTO smart_meter_od(meter_no, action_type, name, flag=1)`. The converter/HES polls this table and reads the meter |
| `ConfigController` → `resources.sock` | Sends a raw text command over TCP (port 8024) to a hard-coded host (the converter/HES), "protected" only by a hard-coded password |
| Master-data screens | CRUD on hierarchy, meter, modem, users, etc. through Hibernate |
| Billing integration | `billing_ws_integration_log` holds JSON exchanged with the external billing web service; the app parses it with Jackson for display |
| Raw data download | Lists and serves files from `rawdata_path/<month>/<meter>/` |

---

## 4. Problems found (most important first)

Severity: **P0** = fix immediately, **P1** = fix soon, **P2** = planned work, **P3** = housekeeping.

### P0 — Critical

1. **Credentials published in a public GitHub repository.** These are all in Git history:
   - `src/resources/database.properties`: MySQL **root** credentials for three environments, including a production-looking IP `10.9.228.99`.
   - `src/resources/SendMail.java` (~line 49): SMTP username and password.
   - `src/resources/SendSMS.java` (~line 144): mail/SMS account password. `ActionBeans/SendSMS_VIPL.java` contains SMS gateway URLs with public IPs.
   - `src/com/rtdas/common/controller/ConfigController.java` (~line 70): hard-coded password that gates raw commands to the converter.
   - Seven JSPs in `WebContent/MasterData/` and `getZones_1.jsp`: a DB password in commented-out code.
   - **Action:** rotate *all* of these now, make the repo private, move secrets to environment or server config, and purge them from history.
2. **No authentication on most of the application.** The `SessionInterceptor` is commented out in `dispatcher-servlet.xml`, so every `*.epx` controller endpoint works without logging in. Only 7 of the 135 directly reachable JSPs check the session, and many of them run SQL. Anyone who can reach the server can read meter and consumer data, insert on-demand requests, and upload files.
3. **SQL / HQL injection everywhere.**
   - The login lookup itself is injectable: `LoginDaoImpl.java:31` builds `"FROM Users WHERE userId='"+userId+"'"`.
   - About 150+ string-concatenated SQL statements in Java, e.g. `D2_DAO`, `UserRoleDaoImpl`, `FeederReportsDao`, `ProcessUploadedBilling.InsertRowInDB`.
   - 59 JSPs build SQL from `request.getParameter(...)`. The application connects as MySQL **root**, so one injection gives full database control.
4. **Spring Framework 3.0.1 (2010)** is affected by **CVE-2010-1622**, a remote class-loader manipulation via data binding that can lead to code execution, among many later CVEs. Both Spring 3.0.1 and 4.1.4 `spring-web` jars are on the classpath.
5. **Path traversal in file download and upload.**
   - `DownloadsController.downloadFiles` and `showFiles` use the `month`, `meter` and `filename` request parameters directly in file paths, so `../../` can read any file on the server.
   - The upload handlers (`/proceed`, `/uploadBillData`, `/processBillData`) write `file.getOriginalFilename()` straight into a path, which can be used to overwrite files, including JSPs (remote code execution).
6. **Unauthenticated remote command channel.** `/sendConfig` forwards arbitrary text to the converter/HES over TCP, protected only by the hard-coded password from item 1.

### P1 — High

7. **Weak password storage.** Passwords are stored as unsalted **SHA-1** (`EncryptPassword`), using `sun.misc.BASE64Encoder`, which does not exist after Java 8. The login prints the plaintext password to stdout (`LoginController`: `System.out.println(... user.getPassword())`), so passwords end up in Tomcat logs.
8. **Login can crash (NullPointerException).** `captcha.isCorrect(...)` is called without a null check when the session has no captcha (direct POST or expired session). There is no account lockout or rate limiting, and no CSRF protection anywhere.
9. **Source is incomplete or does not compile** (89 javac errors; see §2). Missing packages: `com.rtdas.frtuconfig`, `FormBeans`, `in.verticross.db`, `com.vipl.mdm`, Struts. The deployed WAR cannot be reproduced from Git.
10. **Meter-data loader source is missing** (see §3). The most important part of the pipeline, file → database, is not in this repository.
11. **Database connections leak and are not pooled.**
    - `DriverManagerDataSource` opens a new physical connection for every request.
    - `DBUtil.getConnection()` reads the properties file and opens a new connection on every call, and returns `null` on failure, which later causes NullPointerExceptions.
    - `close()` is often skipped on exceptions (no `finally` or try-with-resources), e.g. in `ProcessUploadedBilling.insertIntoDb` and about 75 JSPs.
    - Under load this exhausts MySQL `max_connections`.
12. **Outdated libraries with known vulnerabilities:**
    - `log4j 1.2.14` and `1.2.17` (end of life; CVE-2019-17571, CVE-2021-4104 and others)
    - `commons-fileupload 1.1.1` (CVE-2016-1000031, CVE-2014-0050, CVE-2016-3092, CVE-2023-24998)
    - `commons-collections 2.1.1` (deserialization gadgets)
    - `mysql-connector-java 5.0.5` (2007; many CVEs; no TLS 1.2)
    - `jackson-databind 2.11.1` (several CVEs)
    - `hibernate 3.2.6`, `hibernate-validator 4.0.2`
    - `poi 3.17` together with `poi 4.1.2`; `xmlbeans 2.6` together with `3.1`
    - `json-lib 2.3`, `lucene 2.3.2`, `commons-io 1.1`, `commons-email 1.1`, `jstl 1.2`
13. **Duplicate and conflicting jars** on the classpath (two each of spring-web, POI, xmlbeans, log4j, commons-logging, commons-collections4, jaxb-impl). Which class gets loaded depends on jar order, which can cause random `NoSuchMethodError`s.
14. **No HTTPS or secure-cookie configuration.** The session cookie is not marked `Secure`/`HttpOnly` in `web.xml`, and the captcha and credentials travel in plaintext unless Tomcat terminates TLS.

### P2 — Medium (correctness and performance)

15. **Errors are swallowed.** There are 639 `printStackTrace()` calls and 922 `System.out.println` calls, and many `catch (Exception e) {}` blocks are empty (e.g. `DownloadsController.showFiles`). A failure produces a blank page, and production diagnosis depends on stdout.
16. **Upload handlers report success even on failure.** For example, `processBillData` returns "processed successfully" after a caught exception. Each row in `ProcessUploadedBilling.insertIntoDb` opens a new connection but closes it only once, and computes `pf = kWh/kVAh`, which gives NaN or Infinity when kVAh is 0. Billing timestamps are taken from "now", not from the file.
17. **Dates are handled as strings.** There are many `SimpleDateFormat` patterns (`dd-MM-yyyy`, `yyyy-MM-dd`, `dd/MM/yyyy`) and inserts of formatted strings into date columns. `zeroDateTimeBehavior=convertToNull` hides invalid dates. Time zone handling is implicit, which is a risk for interval (15/30-min) load-profile alignment.
18. **Heavy queries with no pagination or indexes in the code.** Examples are the `select * from d2 where cdfid in (select max(cdfid) … )` pattern, dashboards that scan `meter`, `hierarchy` and `ls_data_stbl` per request, and 2,800-line report DAOs. Index coverage cannot be confirmed without the schema.
19. **Hard-coded Windows paths and environment data.** These include `D:\Autofiles\…` in `web.xml`, `C:\Users\maruthi\Desktop\…` in `rawdata_path`, the converter host and port in `sock.java`, `siteid=3000`, and branches by user name (`"mopuser"` gets a different home page).
20. **Shared mutable state.** `resources.sock` uses a `static Socket`, so concurrent requests can corrupt each other. `ProcessUploadedBilling` keeps `Connection`/`Statement` in instance fields.
21. **Upload size limit is 5 MB** (`multipartResolver`), which may reject large MIOS or Excel files without a message.

### P3 — Low (maintainability)

22. **Large amounts of dead and duplicate code.** Examples: `*_bkp.java` and `*_bkp.jsp`, `Utilities` / `Utilities1` / `utilities` (about 700 near-identical lines each), `AutoResetEnergies` in two packages, `getMetersByssID1/2/3`, `getHierarchyFor360/361/362/363`, `zoom1/2/3`, `test.jsp`, `sample.jsp`, `bartest.jsp`, `pietest.jsp` and the `src/Test` package. Some of these test pages are publicly reachable.
23. **No tests, no build script, no CI, no README, no database schema or migration scripts.**
24. **Inconsistent architecture.** Three generations of code live side by side (Struts-era JDBC, JSP-with-SQL, Spring/Hibernate), and there are five JSON libraries.
25. **Repository bloat.** The repo is about 70 MB packed, mainly from committed jars, source maps (`pdfmake.min.js.map`) and vendor JS. One file, `utils/utilities.java`, is not UTF-8.

---

## 5. Open questions for the team

1. Where is the source of the **DLMS converter** and of whatever **loads the XML/JSON into MySQL**? Are there MySQL stored procedures, events or cron jobs that fill the `analytics_*`, `*_live_status` and `vee_summary_daily` tables?
2. Can we get the **deployed WAR** and a **schema-only dump** (`mysqldump --no-data --routines --events --triggers`) from production?
3. Which Java and Tomcat versions run in production, on which OS (Windows, judging by the paths)? Is TLS terminated in front of Tomcat?
4. Is the app reachable from the internet, or only from the utility's intranet or VPN?
5. Which modules are actually used (prepaid, HES, IEC-104, SLD, Pominda/CESC variants)? Unused modules can be removed instead of fixed.

---

## 6. Proposed remediation plan (awaiting approval)

Each phase is a separate branch and pull request against the baseline, and nothing is deployed without your sign-off.

| Phase | Goal | Main steps | Changes behaviour? |
|---|---|---|---|
| **0. Containment** (now, mostly ops) | Stop the bleeding | Make the GitHub repo **private**; **rotate** the DB, SMTP, SMS and converter passwords; create a least-privilege MySQL user for the app instead of `root`; firewall the app and the converter port 8024 to trusted networks | No code change |
| **1. Recover the full picture** | Know what is really running | Obtain the production WAR, schema dump and loader/converter source; diff the WAR against the repo; commit the missing sources and schema (DDL only) | No |
| **2. Reproducible build** | Build from Git | Add a **Maven** `pom.xml` that mirrors the current jars exactly (same versions, Java 8 target); exclude or stub dead classes that do not compile (Struts `ActionBeans`, `Test`, `_bkp`); get a WAR building in CI (GitHub Actions); smoke-test on Tomcat 8.5 + MySQL with the production schema | No (same jars) |
| **3. Secrets out of code** | No credentials in Git | Read DB, SMTP, SMS, converter and paths from environment variables or a Tomcat JNDI resource; delete the hard-coded values and commented JDBC lines; purge history (`git filter-repo`) after rotation | Config only |
| **4. Critical security fixes** | Close P0 holes | Re-enable and fix `SessionInterceptor`, plus a servlet `Filter` protecting the direct JSPs; parameterize the login query and all injectable SQL, starting with internet-facing and write paths; fix path traversal in download and upload (whitelist and canonical-path check); protect or remove `/sendConfig`; captcha null check; remove plaintext password logging | Yes — security only, no functional change |
| **5. Passwords** | Modern hashing | Add BCrypt with transparent upgrade on next login (verify SHA-1 once, then re-hash); remove `sun.misc` usage | Users keep their passwords |
| **6. Stability** | Stop leaks and silent failures | Pooled DataSource (Tomcat JDBC/HikariCP via JNDI); try-with-resources in `DBUtil` and DAOs; replace `printStackTrace`/`println` with SLF4J + Logback; return real error codes from upload endpoints; fix the `pf` divide-by-zero | Minor (better errors) |
| **7. Library upgrades** (staged, each tested) | Remove known CVEs | Remove duplicate jars; upgrade MySQL Connector/J → 8.x, commons-fileupload → 1.5, log4j 1 → Logback/reload4j, Jackson → 2.17, POI → 5.x; then Spring 3.0 → 5.3 and Hibernate 3 → 5.6 (the largest step, needs regression testing); Tomcat 9; Java 11/17 | Needs full regression testing |
| **8. Performance** | Faster reports | Add indexes from the schema review (`meterid`, `cdfid`, timestamps); replace `max(cdfid)` subqueries with `*_curr` tables or window queries; pagination on large lists | Faster, same output |
| **9. Clean-up** | Maintainability | Delete dead code, `_bkp` files and test pages; consolidate the duplicate utility classes; move inline-SQL JSPs to controllers/DAOs step by step; add README, schema migrations (Flyway) and basic integration tests | No |

**Recommended order:** start Phase 0 today (it needs no code). Then do 1 → 2 → 3 → 4 as the first code milestone, followed by 5–6. Phases 7–9 are larger and can be scheduled once there is a reproducible build and a test environment with production-like data.

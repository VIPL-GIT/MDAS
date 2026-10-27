package utils;
import java.util.*;

public class MeterStatusBean {
  private List  commMeters;
  private List  notCommMeters;
  private List neverCommMeters;
  private List powerOffMeters;
  private List mcfMeters;
  private List unmappedCommMeters;
  
  
  
    public List getUnmappedCommMeters() {
	  return unmappedCommMeters;
   }

public void setUnmappedCommMeters(List unmappedCommMeters) {
	this.unmappedCommMeters = unmappedCommMeters;
}

	public List getMcfMeters() {
	return mcfMeters;
}

public void setMcfMeters(List mcfMeters) {
	this.mcfMeters = mcfMeters;
}

	/**
     * @return the commMeters
     */
    public List getCommMeters() {
        return commMeters;
    }

    /**
     * @param commMeters the commMeters to set
     */
    public void setCommMeters(List commMeters) {
        this.commMeters = commMeters;
    }

    /**
     * @return the notCommMeters
     */
    public List getNotCommMeters() {
        return notCommMeters;
    }

    /**
     * @param notCommMeters the notCommMeters to set
     */
    public void setNotCommMeters(List notCommMeters) {
        this.notCommMeters = notCommMeters;
    }

    /**
     * @return the neverCommMeters
     */
    public List getNeverCommMeters() {
        return neverCommMeters;
    }

    /**
     * @param neverCommMeters the neverCommMeters to set
     */
    public void setNeverCommMeters(List neverCommMeters) {
        this.neverCommMeters = neverCommMeters;
    }
    /**
     * @return the powerOffMeters
     */
    public List getPowerOffMeters() {
        return powerOffMeters;
    }

    /**
     * @param powerOffMeters the powerOffMeters to set
     */
    public void setPowerOffMeters(List powerOffMeters) {
        this.powerOffMeters = powerOffMeters;
    }    
}

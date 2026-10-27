package resources;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.captcha.Captcha;
import nl.captcha.backgrounds.FlatColorBackgroundProducer;
import nl.captcha.gimpy.DropShadowGimpyRenderer;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.servlet.SimpleCaptchaServlet;
import nl.captcha.text.producer.DefaultTextProducer;
import nl.captcha.text.renderer.DefaultWordRenderer;

/**
 *
 * @author maruthi
 */
public class MySimpleCaptcha extends SimpleCaptchaServlet {

    private static final String PARAM_HEIGHT = "height";
    private static final String PARAM_WIDTH = "width";
    protected int _width = 310;
    protected int _height = 50;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	List<java.awt.Color> textColors = Arrays.asList(Color.BLACK, Color.BLACK, Color.BLACK);
    		List<java.awt.Font> textFonts = Arrays.asList(new Font("Arial", Font.ITALIC, 40),new Font("Courier", Font.ITALIC, 40));
    			
        Captcha captcha = new Captcha.Builder(_width, _height)
            .addText(new DefaultTextProducer(), 
                    new DefaultWordRenderer(textColors, textFonts))
            //.addBackground(new GradiatedBackgroundProducer())
            .addBackground(new FlatColorBackgroundProducer(Color.LIGHT_GRAY))
            // Add here whatever you need
             
            .addNoise()
            .gimp(new DropShadowGimpyRenderer())
            .addBorder()
            .build();
  
            
        CaptchaServletUtil.writeImage(resp, captcha.getImage());
        req.getSession().setAttribute(Captcha.NAME, captcha);
     }

 }

package com.abisgen.main;

import java.awt.TextArea;
import java.text.SimpleDateFormat;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

public class TextAreaLog4jAppender extends AppenderSkeleton {
    private TextArea ta;

    public TextAreaLog4jAppender (TextArea ta_obj){
        super();
        ta = ta_obj;
        setLayout(new org.apache.log4j.PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n"));
    }
    
    @Override
    public void close() {
    }

    @Override
    public boolean requiresLayout() {
        return true;
    }

    @Override
    protected void append(LoggingEvent le) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(le.getTimeStamp());
        ta.append( String.valueOf(date + le.getLevel().toString() + "  " + le.getRenderedMessage()) );
    }

}

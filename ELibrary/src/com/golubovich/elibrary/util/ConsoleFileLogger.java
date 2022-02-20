package com.golubovich.elibrary.util;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class ConsoleFileLogger extends ConsoleHandler {
    private static final Logger LOG = Logger.getLogger(ConsoleFileLogger.class.getName());
    private FileHandler fileHandler;

    public ConsoleFileLogger() {
        try {
            this.fileHandler = new FileHandler();
        } catch (Exception var2) {
            LOG.log(Level.SEVERE, (String)null, var2);
        }

    }

    public void close() {
        super.close();
        if (this.fileHandler != null) {
            this.fileHandler.close();
        }

    }

    public void publish(LogRecord record) {
        super.publish(record);
        if (this.fileHandler != null) {
            this.fileHandler.publish(record);
        }

    }
}

package com.zsy.springframework.context.support;

public class ClappPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private String[] configLocations;

    public ClappPathXmlApplicationContext() {
    }

    public ClappPathXmlApplicationContext(String configLocation) {
        this(new String[]{configLocation});
    }

    public ClappPathXmlApplicationContext(String[] configLocations) {
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    public String[] getConfigLocations() {
        return configLocations;
    }
}

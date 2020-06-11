package com.cloudmusic.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@Component
//@ConfigurationProperties(prefix = "resource")
public class ResourceProperties {

    private List<Resource> resourceList = new ArrayList<>();

    public static class Resource{
        private String handler;
        private String[] locations;

        public String getHandler() {
            return handler;
        }

        public void setHandler(String handler) {
            this.handler = handler;
        }

        public String[] getLocations() {
            return locations;
        }

        public void setLocations(String[] locations) {
            this.locations = locations;
        }

        @Override
        public String toString() {
            return "Resource{" +
                    "handler='" + handler + '\'' +
                    ", locations=" + Arrays.toString(locations) +
                    '}';
        }
    }

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }

    @Override
    public String toString() {
        return "ResourceProperties{" +
                "resourceList=" + resourceList.toString() +
                '}';
    }
}

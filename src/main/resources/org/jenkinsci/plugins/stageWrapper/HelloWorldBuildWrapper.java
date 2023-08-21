package org.jenkinsci.plugins.stageWrapper;


import hudson.Extension;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.tasks.BuildWrapper;
import hudson.tasks.BuildWrapperDescriptor;
import org.kohsuke.stapler.DataBoundConstructor;

import java.io.IOException;
import java.util.logging.Logger;

public class HelloWorldBuildWrapper extends BuildWrapper {

    private static final Logger LOGGER = Logger.getLogger(HelloWorldBuildWrapper.class.getName());

    @DataBoundConstructor
    public HelloWorldBuildWrapper() {
        // Constructor for Data Binding from Jenkins UI
    }

    @Override
    public Environment setUp(AbstractBuild build, Launcher launcher, BuildListener listener) throws IOException {
        listener.getLogger().println("Hello, World! Before Stage.");

        return new Environment() {
            @Override
            public boolean tearDown(AbstractBuild build, BuildListener listener) throws IOException, InterruptedException {
                listener.getLogger().println("Hello, World! After Stage.");
                return true;
            }
        };
    }

    @Override
    public DescriptorImpl getDescriptor() {
        return (DescriptorImpl) super.getDescriptor();
    }

    @Extension
    public static final class DescriptorImpl extends BuildWrapperDescriptor {

        @Override
        public boolean isApplicable(AbstractProject<?, ?> item) {
            return true;
        }

        @Override
        public String getDisplayName() {
            return "Hello World Build Wrapper";
        }
    }
}
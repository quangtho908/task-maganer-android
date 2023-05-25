package com.taskmanager.horkrux.Models;

import java.util.Map;

public class Workspace {
    private String id;
    private String name;
    private Map<String, Users> members;
    private Map<String, Users> admins;
    private String createdBy;
    public Workspace() {
    }

    public Workspace(String name, String id, String createdBy) {
        this.name = name;
        this.id = id;
        this.createdBy = createdBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Users> getMembers() {
        return members;
    }

    public Map<String, Users> getAdmins() {
        return admins;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setMembers(Map<String, Users> members) {
        this.members = members;
    }

    public void setAdmins(Map<String, Users> admins) {
        this.admins = admins;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}

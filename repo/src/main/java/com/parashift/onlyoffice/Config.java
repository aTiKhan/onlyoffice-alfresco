package com.parashift.onlyoffice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.WebScriptRequest;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/*
    Copyright (c) Ascensio System SIA 2021. All rights reserved.
    http://www.onlyoffice.com
*/
@Component(value = "webscript.onlyoffice.onlyoffice-config.get")
public class Config extends DeclarativeWebScript {

    @Autowired
    ConfigManager configManager;

    @Override
    protected Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("docurl", configManager.getOrDefault("url", "http://127.0.0.1/"));
        model.put("docinnerurl", configManager.getOrDefault("innerurl", ""));
        model.put("alfurl", configManager.getOrDefault("alfurl", ""));

        model.put("cert", getBoolAsAttribute("cert", "false"));
        model.put("forcesave", getBoolAsAttribute("forcesave", "false"));
        model.put("webpreview", getBoolAsAttribute("webpreview", "false"));

        model.put("jwtsecret", configManager.getOrDefault("jwtsecret", ""));

        model.put("formatODT", getBoolAsAttribute("formatODT", "false"));
        model.put("formatODS", getBoolAsAttribute("formatODS", "false"));
        model.put("formatODP", getBoolAsAttribute("formatODP", "false"));
        model.put("formatCSV", getBoolAsAttribute("formatCSV", "true"));
        model.put("formatTXT", getBoolAsAttribute("formatTXT", "true"));
        model.put("formatRTF", getBoolAsAttribute("formatRTF", "false"));
        return model;
    }

    private String getBoolAsAttribute(String key, Object defaultValue) {
        return configManager.getAsBoolean(key, defaultValue) ? "checked=\"\"" : "";
    }
}


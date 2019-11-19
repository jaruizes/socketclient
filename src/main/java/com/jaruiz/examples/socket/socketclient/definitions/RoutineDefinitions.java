package com.jaruiz.examples.socket.socketclient.definitions;


import com.jaruiz.examples.socket.socketclient.definitions.impl.RoutineDef;
import com.jaruiz.examples.socket.socketclient.definitions.impl.RoutineDefs;
import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.binder.DigesterLoader;
import org.apache.commons.digester3.binder.RulesModule;
import org.apache.commons.digester3.xmlrules.FromXmlRulesModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

public class RoutineDefinitions {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoutineDefinitions.class);

    protected Digester digester = null;
    private RoutineDefs definitions;
    private String definitionsFile;

    public RoutineDefinitions(String definitionsFile) {
        this.definitionsFile = definitionsFile;
        this.init();
        LOGGER.info("Created");
    }

    public RoutineDef get(String routineName) throws RoutineDefinitionNotFoundException {
        final RoutineDef def = this.definitions.getRoutineDef(routineName);
        if (def == null) {
            throw new RoutineDefinitionNotFoundException(routineName, this.definitionsFile);
        }

        LOGGER.info("Rutine [{}] got from definitions", routineName);

        return def;
    }

    public void init() {
        this.initRules();
        this.initDefinitions();
    }

    private void initRules() {
        if (this.digester == null) {
            final InputStream rulesIS = this.getIS("rules/RoutineCallerRules.xml");
            RulesModule rules = new FromXmlRulesModule() {
                @Override
                protected void loadRules() {
                    loadXMLRules(rulesIS);
                }
            };

            final DigesterLoader loader = DigesterLoader.newLoader(rules);
            this.digester = loader.newDigester();

            LOGGER.info("Routine Definitions rules initialized");
        }
    }

    private void initDefinitions() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(this.definitionsFile);
            this.definitions = this.digester.parse(inputStream);

            LOGGER.info("Routine Definitions loaded");
        } catch (IOException e) {
            // TODO: Manage exception
        } catch (SAXException e) {
            // TODO: Manage exception
        }
    }

    private InputStream getIS(String file) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(file);
        return inputStream;
    }
    protected void destroyConfigDigester() {
        this.digester = null;
    }
}

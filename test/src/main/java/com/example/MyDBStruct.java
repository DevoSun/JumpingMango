package com.example;


import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDBStruct {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.example.sunpengfei.myapplication.db");
        addNote(schema);
        new DaoGenerator().generateAll(schema,"/Users/sunpengfei/AndroidStudioProjects/test/MyApplication/app/src/main/java-gen");

    }

    private static void addNote(Schema schema){
        Entity entity = schema.addEntity("Note");
        entity.setTableName("note");
        entity.addIdProperty();

        entity.addStringProperty("name");
        entity.addStringProperty("content");
        entity.addIntProperty("noteId");
    }
}

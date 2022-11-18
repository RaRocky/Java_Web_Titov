package org.example.lesson5;

public class Locator {

    private String name;
    private String type;
    private String path;

    Locator(String name, String type, String path) {
        setType(type);
        this.path = path;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type.equals("id") || type.equals("css") || type.equals("xpath")) this.type = type;
        else System.out.println("Тип локатора " + getPath() +
                " указан неверно. Возможные варианты: 'id', 'css', 'xpath.'");
    }

    public String getPath() {
        return path;
    }
}

package fr.yanissou.customwhitelist;

public enum Trads {
    PREFIX("§8[§e§lCUSTOMWHITELIST§8]§r "),
    USAGE("§cUsage: /cwhitelist <add/remove/clear/list/read>");

    private String message;
    Trads(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}

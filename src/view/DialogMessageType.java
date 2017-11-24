package view;

public enum DialogMessageType {
    //Confirmation
    DATABASE_ADD,
    DATABASE_EDIT,
    DATABASE_REMOVE,
    PASSWORD_CHANGE,
    APP_EXIT,

    //Warning
    INVALID_INPUT,
    INVALID_PAYMENT,
    RESTRICTED_ACCESS,

    //Information
    TRANSACTION_COMPLETE,
    SETTINGS_SAVED
}

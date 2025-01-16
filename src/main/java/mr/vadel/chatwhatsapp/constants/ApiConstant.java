package mr.vadel.chatwhatsapp.constants;

public class ApiConstant {

    public static final String API_URL = "/api/v1";
    public static final String CHAT_URL = API_URL + "/chats";
    public static final String MESSAGE_URL = API_URL + "/messages";
    public static final String MESSAGE_URL_UPLOAD_MEDIA = MESSAGE_URL + "/upload-media";
    public static final String GET_MESSAGES = MESSAGE_URL + "/chat/{chat-id}";
    public static final String CONSUMES_FILE = "multipart/form-data";
    public static final String USER_URL = API_URL + "/users";

    private ApiConstant() {}
}

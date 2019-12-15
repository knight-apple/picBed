package cn.knightapple.core.model;

public interface IStatusMessage {
    String getCode();

    String getMessage();

    enum SystemStatus implements IStatusMessage {

        SUCCESS("1000", "操作成功"), //请求成功
        ERROR("1001", "网络异常，请稍后重试~"),
        FILE_UPLOAD_NULL("1002","图片上传异常"),
        FILE_UPLOAD_ERROR("1003","上传错误"),
        PARAM_ERROR("1004","请求失败" ),     //请求失败
        FILE_EXISIT("1005","文件已存在" );      //请求失败

        private String code;
        private String message;

        SystemStatus(String code, String message) {
            this.code = code;
            this.message = message;
        }
        @Override
        public String getCode() {
            return this.code;
        }
        @Override
        public String getMessage() {
            return this.message;
        }
    }
}
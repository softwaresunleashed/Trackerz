package com.unleashed.android.trackerz.HttpRetrofit.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;





public class LoginModel {

    public class Data {

        @SerializedName("auth_token")
        @Expose
        private String authToken;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("email")
        @Expose
        private String email;

        /**
         * No args constructor for use in serialization
         */
        public Data() {
        }

        /**
         * @param lastName
         * @param email
         * @param authToken
         * @param firstName
         */
        public Data(String authToken, String firstName, String lastName, String email) {
            this.authToken = authToken;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }

        /**
         * @return The authToken
         */
        public String getAuthToken() {
            return authToken;
        }

        /**
         * @param authToken The auth_token
         */
        public void setAuthToken(String authToken) {
            this.authToken = authToken;
        }

        /**
         * @return The firstName
         */
        public String getFirstName() {
            return firstName;
        }

        /**
         * @param firstName The first_name
         */
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        /**
         * @return The lastName
         */
        public String getLastName() {
            return lastName;
        }

        /**
         * @param lastName The last_name
         */
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        /**
         * @return The email
         */
        public String getEmail() {
            return email;
        }

        /**
         * @param email The email
         */
        public void setEmail(String email) {
            this.email = email;
        }

    }

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("version")
    @Expose
    private String version;

    /**
     * No args constructor for use in serialization
     */
    public LoginModel() {
    }

    /**
     * @param message
     * @param status
     * @param data
     * @param version
     */
    public LoginModel(String status, String message, Data data, String version) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.version = version;
    }

    /**
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return The data
     */
    public Data getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(Data data) {
        this.data = data;
    }

    /**
     * @return The version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version The version
     */
    public void setVersion(String version) {
        this.version = version;
    }
}

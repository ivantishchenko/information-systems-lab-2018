package ch.ethz.brandin.response_models;

import ch.ethz.brandin.models.Comparison;

public class UserInfoResponse implements IResponse {

    private Comparison data;
    private String fullName1;
    private String fullName2;
    private boolean privacyFlag1;
    private boolean privacyFlag2;
    private String picUrl1;
    private String picUrl2;

    public UserInfoResponse(Comparison data) {
        this.data = data;
    }

    @Override
    public void generateResponse() {
        fullName1 = data.getUser1().getFullName();
        fullName2 = data.getUser2().getFullName();

        privacyFlag1 = data.getUser1().isPrivate();
        privacyFlag2 = data.getUser2().isPrivate();
        picUrl1 = data.getUser1().getProfilePicUrl();
        picUrl2 = data.getUser2().getProfilePicUrl();

    }

    public boolean isPrivacyFlag1() {
        return privacyFlag1;
    }

    public void setPrivacyFlag1(boolean privacyFlag1) {
        this.privacyFlag1 = privacyFlag1;
    }

    public boolean isPrivacyFlag2() {
        return privacyFlag2;
    }

    public void setPrivacyFlag2(boolean privacyFlag2) {
        this.privacyFlag2 = privacyFlag2;
    }

    public String getPicUrl1() {
        return picUrl1;
    }

    public void setPicUrl1(String picUrl1) {
        this.picUrl1 = picUrl1;
    }

    public String getPicUrl2() {
        return picUrl2;
    }

    public void setPicUrl2(String picUrl2) {
        this.picUrl2 = picUrl2;
    }

    public String getFullName1() {
        return fullName1;
    }

    public void setFullName1(String fullName1) {
        this.fullName1 = fullName1;
    }

    public String getFullName2() {
        return fullName2;
    }

    public void setFullName2(String fullName2) {
        this.fullName2 = fullName2;
    }

}

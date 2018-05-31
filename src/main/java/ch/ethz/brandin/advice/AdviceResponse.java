package ch.ethz.brandin.advice;

import ch.ethz.brandin.response_models.IResponse;

public class AdviceResponse implements IResponse{
    private String advice = "";

    public AdviceResponse(String advice) {
        this.advice = advice;
    }

    @Override
    public void generateResponse() {}

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}
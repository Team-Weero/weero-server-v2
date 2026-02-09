package team.weero.app.adapter.in.web.auth.dto.request;

public record SignInCommand(
    String email,
    String password) {}

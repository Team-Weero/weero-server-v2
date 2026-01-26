 package team.weero.app.application.exception.teacher.error;

  import team.weero.app.global.error.ErrorProperty;

  public enum TeacherErrorCode implements ErrorProperty {
    TEACHER_NOT_FOUND(404, "Teacher Not Found", 1);

    private final int status;
    private final String message;
    private final int sequence;

    TeacherErrorCode(int status, String message, int sequence) {
      this.status = status;
      this.message = message;
      this.sequence = sequence;
    }

    @Override
    public int status() {
      return status;
    }

    @Override
    public String message() {
      return message;
    }

    @Override
    public String code() {
      return "TEACHER-" + status + "-" + sequence;
    }
  }
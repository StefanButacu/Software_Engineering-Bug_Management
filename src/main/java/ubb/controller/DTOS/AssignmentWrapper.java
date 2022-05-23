package ubb.controller.DTOS;

public class AssignmentWrapper {
    Long idBug;
    Long idProgrammer;

    public Long getIdBug() {
        return idBug;
    }

    public void setIdBug(Long idBug) {
        this.idBug = idBug;
    }

    public Long getIdProgrammer() {
        return idProgrammer;
    }

    public void setIdProgrammer(Long idProgrammer) {
        this.idProgrammer = idProgrammer;
    }

    public AssignmentWrapper() {
    }

    public AssignmentWrapper(Long idBug, Long idProgrammer) {
        this.idBug = idBug;
        this.idProgrammer = idProgrammer;
    }
}

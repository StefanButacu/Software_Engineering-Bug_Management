package ubb.controller.DTOS;


public class EmployeeDTO {

        private Long id;
        private String username;
        private String password;
        private RoleDTO role;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public RoleDTO getRole() {
                return role;
        }

        public void setRole(RoleDTO role) {
                this.role = role;
        }

        public EmployeeDTO() {
        }

}

package com.AmoraEstoque.AmoraEstoque.dto;

public class CompanyResponseDTO {

    private Long companyId;
    private String companyName;
    private String email;
    private String usernameGerado;
    private String senhaGerada;

    public CompanyResponseDTO(Long companyId, String companyName, String email,
                               String usernameGerado, String senhaGerada) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.email = email;
        this.usernameGerado = usernameGerado;
        this.senhaGerada = senhaGerada;
    }

    public Long getCompanyId() { return companyId; }
    public String getCompanyName() { return companyName; }
    public String getEmail() { return email; }
    public String getUsernameGerado() { return usernameGerado; }
    public String getSenhaGerada() { return senhaGerada; }
}
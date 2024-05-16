package ma.adria.document_validation.gateway.dto;

import lombok.*;

import java.util.UUID;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientDetailsResponseDTO {
    protected String id;
    private String codeApp;
    private String statut;
    private String name;
    private int nbrMaxTransactions;
    private int sizeMax;
    private String secret;
    private  String companyName;
}
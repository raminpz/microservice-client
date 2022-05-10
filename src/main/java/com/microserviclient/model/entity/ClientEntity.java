package com.microserviclient.model.entity;

import com.microserviclient.model.dto.ClientType;
import com.microserviclient.model.dto.EnterpriseInfo;
import com.microserviclient.model.dto.PersonInfo;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;



@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document(collection = "Clients")
public class ClientEntity {

    @Id
    private String id;
    private ClientType clientType; // Personal or enterprise
    private String status; // active or inactive
    private PersonInfo personInfo;
    private EnterpriseInfo enterpriseInfo;
    private Date createDate;
    private Date updateDate;
    private Date lastOperationUpdate;
}

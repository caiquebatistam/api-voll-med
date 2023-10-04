package med.voll.api.model.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.model.dto.AddressDto;


@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Address {

    @Column(name = "logradouro")
    private String streetName;
    @Column(name = "bairro")
    private String neighborhood;
    @Column(name = "ceo")
    private String zipCode;
    @Column(name = "numero")
    private String number;
    @Column(name = "complemento")
    private String complement;
    @Column(name = "cidade")
    private String city;
    @Column(name = "uf")
    private String uf;

    @Override
    public String toString() {
        return "Endereco{" +
                "logradouro='" + streetName + '\'' +
                ", bairro='" + neighborhood + '\'' +
                ", cep='" + zipCode + '\'' +
                ", numero='" + number + '\'' +
                ", complemento='" + complement + '\'' +
                ", cidade='" + city + '\'' +
                ", uf='" + uf + '\'' +
                '}';
    }

    public Address(AddressDto addressDto){
        this.streetName = addressDto.streetName();
        this.neighborhood = addressDto.neighborhood();
        this.zipCode = addressDto.zipCode();
        this.number = addressDto.number();
        this.complement = addressDto.complement();
        this.city = addressDto.city();
        this.uf = addressDto.uf();
    }

    public void update(AddressDto addressDto){
        if(addressDto.streetName() != null){
            this.streetName = addressDto.streetName();
        }
        if(addressDto.neighborhood() != null){
            this.neighborhood = addressDto.neighborhood();
        }
        if(addressDto.zipCode() != null){
            this.zipCode = addressDto.zipCode();
        }
        if(addressDto.number() != null){
            this.number = addressDto.number();
        }
        if(addressDto.complement() != null){
            this.complement = addressDto.complement();
        }
        if(addressDto.city() != null){
            this.city = addressDto.city();
        }
        if(addressDto.uf() != null){
            this.uf = addressDto.uf();
        }
    }
}

package kz.diploma.exchange_rate.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Setter;

import java.util.List;

@XmlRootElement(name = "rates")
@Setter
public class Rates {

    private List<Rate> rates;

    @XmlElement(name = "item")
    public List<Rate> getRates() {
        return rates;
    }
}

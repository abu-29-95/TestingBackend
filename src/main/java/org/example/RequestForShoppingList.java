
package org.example;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "item",
    "aisle",
    "parse"
})
@Data
public class RequestForShoppingList {

    @JsonProperty("item")
    private String item;
    @JsonProperty("aisle")
    private String aisle;
    @JsonProperty("parse")
    private Boolean parse;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("item")
    public String getItem() {
        return item;
    }

    @JsonProperty("item")
    public String setItem(String item) {
        this.item = item;
        return this.item;
    }

    public RequestForShoppingList withItem(String item) {
        this.item = item;
        return this;
    }

    @JsonProperty("aisle")
    public String getAisle() {
        return aisle;
    }

    @JsonProperty("aisle")
    public String setAisle(String aisle) {
        this.aisle = aisle;
        return this.aisle;
    }

    public RequestForShoppingList withAisle(String aisle) {
        this.aisle = aisle;
        return this;
    }

    @JsonProperty("parse")
    public Boolean getParse() {
        return parse;
    }

    @JsonProperty("parse")
    public Object setParse(Boolean parse) {
        this.parse = parse;
        return this.parse;
    }

    public RequestForShoppingList withParse(Boolean parse) {
        this.parse = parse;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public RequestForShoppingList withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }



}

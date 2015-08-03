package abassawo.c4q.nyc.fe_nyc;

/**
 * Created by c4q-tashasmith on 8/2/15.
 */
public class Program {

    private String providerName;
    private String providerDescription;
    private String website;

    public String getProviderDescription() {
        return providerDescription;
    }

    public void setProviderDescription(String providerDescription) {
        this.providerDescription = providerDescription;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return
                " Provider Name: " + providerName + '\'' +
                        " Description: " + providerDescription + '\'' +
                        " Website: " + website + '\'';
    }

}

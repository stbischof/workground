package org.eclipse.daanse.xmla.client.soapmessage;

import org.eclipse.daanse.xmla.api.XmlaService;
import org.eclipse.daanse.xmla.api.discover.mdschema.demensions.MdSchemaDimensionsResponseRow;
import org.eclipse.daanse.xmla.model.record.discover.PropertiesR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.demensions.MdSchemaDimensionsRequestR;
import org.eclipse.daanse.xmla.model.record.discover.mdschema.demensions.MdSchemaDimensionsRestrictionsR;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class TestClient {

    public static void main(String[] args) {
        XmlaService client  = new XmlaServiceClientImpl("https://datacube-stage.nomad-dmz.jena.de/cube/xmla");
        PropertiesR properties = new PropertiesR();


        MdSchemaDimensionsRestrictionsR restrictions = new MdSchemaDimensionsRestrictionsR(
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty());
        MdSchemaDimensionsRequestR request = new MdSchemaDimensionsRequestR(properties, restrictions);
        List<MdSchemaDimensionsResponseRow> response = client.discover().mdSchemaDimensions(request);
        assertThat(response).isNotEmpty();
    }

}

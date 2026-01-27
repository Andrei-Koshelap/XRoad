package com.digivikings.adapter.transport.impl;

public final class XroadSoapTemplates {
    private XroadSoapTemplates() {}

    public static String ehitiseAndmeteParing(String cadastralId, String dataVector) {
        String xroadId = java.util.UUID.randomUUID().toString();

        return """
            <?xml version="1.0" encoding="utf-8"?>
            <SOAP-ENV:Envelope
                xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/"
                xmlns:xroad="http://x-road.eu/xsd/xroad.xsd"
                xmlns:id="http://x-road.eu/xsd/identifiers">
              <SOAP-ENV:Header>
                <xroad:client id:objectType="SUBSYSTEM">
                  <id:xRoadInstance>EE</id:xRoadInstance>
                  <id:memberClass>COM</id:memberClass>
                  <id:memberCode>10307231</id:memberCode>
                  <id:subsystemCode>10307231-xtehr</id:subsystemCode>
                </xroad:client>

                <xroad:service id:objectType="SERVICE">
                  <id:xRoadInstance>EE</id:xRoadInstance>
                  <id:memberClass>GOV</id:memberClass>
                  <id:memberCode>70003098</id:memberCode>
                  <id:subsystemCode>ehr3</id:subsystemCode>
                  <id:serviceCode>ehitiseAndmeteParing</id:serviceCode>
                  <id:serviceVersion>v1</id:serviceVersion>
                </xroad:service>

                <xroad:id>%s</xroad:id>
                <xroad:protocolVersion>4.0</xroad:protocolVersion>
              </SOAP-ENV:Header>

              <SOAP-ENV:Body>
                <xroad:ehitiseAndmeteParing>
                  <request>
                    <katastritunnus>%s</katastritunnus>
                    <andmevektor>%s</andmevektor>
                  </request>
                </xroad:ehitiseAndmeteParing>
              </SOAP-ENV:Body>
            </SOAP-ENV:Envelope>
            """.formatted(xroadId, cadastralId, dataVector);
    }
}

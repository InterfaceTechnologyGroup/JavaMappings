<xsl:stylesheet version="1.0" 
 xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:ep="urn:itelligence.tr:pi:fi:bankahesapozeti:isbankasi"
>
 <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>


   <!-- identity transform -->
    <xsl:template match="@*|node()">
      <xsl:copy>
       <xsl:apply-templates select="@*|node()"/>
    </xsl:copy>
 </xsl:template>

  <xsl:template match="XMLEXBAT">
    <ep:XMLEXBAT>
    <xsl:apply-templates select="node()|@*"/>
    </ep:XMLEXBAT>
   </xsl:template>

 <xsl:template match="Extension|Extension//*">
<xsl:element name="gk:{name()}">
    <xsl:apply-templates select="node()|@*"/>
  </xsl:element>
  </xsl:template>

 <xsl:template match="Header|Header//*">
<xsl:element name="sh:{name()}">
    <xsl:apply-templates select="node()|@*"/>
   </xsl:element>
   </xsl:template>
</xsl:stylesheet>
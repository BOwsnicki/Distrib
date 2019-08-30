<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <xsl:template match="/">
        <html>
            <head>
                <title>My recipe</title>
                <link rel="stylesheet" href="reciphtml.css" type="text/css" media="screen" />
            </head>
            <body>
                <xsl:apply-templates />
            </body>
        </html>
    </xsl:template>

    <xsl:template match="recipe">
        <h3>Ingredients</h3>
        <ul>
            <xsl:for-each select="ingredient">
                <li>
                    <xsl:value-of select="@amount"/>
                    <xsl:text> </xsl:text>
                    <xsl:value-of select="@unit"/>
                    <xsl:text> </xsl:text>
                    <xsl:value-of select="."/>
                </li>
            </xsl:for-each>
        </ul>
        <h3>Instructions</h3>
        <ol>
            <xsl:for-each select="instructions/step">
                <li>
                    <xsl:value-of select="."/>
                </li>
            </xsl:for-each>
        </ol>
    </xsl:template>
</xsl:stylesheet>
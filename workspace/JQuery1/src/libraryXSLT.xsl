<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : transform.xsl
    Created on : December 18, 2010, 3:58 PM
    Author     : bernd
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <html>
            <head>
                <link rel="stylesheet" href="libhtml.css" type="text/css" media="screen" />
            </head>
            <body>
                <div id="main">
                    <h1>Our DVD offers</h1>
                    <table border="0" cellpadding="6" cellspacing="3" width="100%" >
                        <thead>
                            <tr>
                                <td>Title</td>
                                <td>Format</td>
                                <td>Genre</td>
                            </tr>
                        </thead>
                        <xsl:for-each select="library/dvd">
                            <xsl:sort select="title" />
                            <tr>
                                <td class="title">
                                    <xsl:value-of select="title" />
                                </td>
                                <td>
                                    <xsl:value-of select="format" />
                                </td>
                                <td>
                                    <xsl:value-of select="genre" />
                                </td>
                            </tr>
                        </xsl:for-each>
                    </table>
                </div>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>

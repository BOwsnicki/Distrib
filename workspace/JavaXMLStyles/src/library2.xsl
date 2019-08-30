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
                <title>DVD library</title>
                <link rel="stylesheet" href="libhtml.css" type="text/css" media="screen" />
            </head>
            <body>
                <div id="main">
                    <h1>Ahoy, har we have some dvds, Gar!</h1>
                    <xsl:for-each select="library/dvd">
                        <xsl:sort select="title" />
                        <p>
                            Yay, the title is <xsl:value-of select="title " /><br />
                            Err, it is made for <xsl:value-of select="format " /><br />
                            And it is like <xsl:value-of select="genre " /><br />
                        </p>
                    </xsl:for-each>
Ye'll ne'er get me buried booty, err!
                </div>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>

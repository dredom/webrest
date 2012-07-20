// Copyright (c) 2010 - Level Studios
// All rights reserved.
package com.lvl.au.wadl;

import java.util.List;

import com.sun.jersey.api.wadl.config.WadlGeneratorConfig;
import com.sun.jersey.api.wadl.config.WadlGeneratorDescription;
import com.sun.jersey.server.wadl.generators.WadlGeneratorApplicationDoc;
import com.sun.jersey.server.wadl.generators.WadlGeneratorGrammarsSupport;
import com.sun.jersey.server.wadl.generators.resourcedoc.WadlGeneratorResourceDocSupport;

/**
 * This subclass of {@link WadlGeneratorConfig} defines/configures {@link WadlGenerator}s
 * to be used for generating WADL.
 */
public class DaWadlGeneratorConfig extends WadlGeneratorConfig {

	/* (non-Javadoc)
	 * @see com.sun.jersey.api.wadl.config.WadlGeneratorConfig#configure()
	 */
	@Override
	public List<WadlGeneratorDescription> configure() {
//        return generator( WadlGeneratorApplicationDoc.class )
//        .prop( "applicationDocsFile", "classpath:/application-doc.xml" )
//        .generator( WadlGeneratorGrammarsSupport.class )
//        .prop( "grammarsFile", "classpath:/application-grammars.xml" )
//        .generator( WadlGeneratorResourceDocSupport.class )
//        .prop( "resourceDocFile", "classpath:/resourcedoc.xml" )
//        .descriptions();
      return generator( WadlGeneratorResourceDocSupport.class )
      .prop( "resourceDocFile", "classpath:/resourcedoc.xml" )
      .descriptions();

	}

}

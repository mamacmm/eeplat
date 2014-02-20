package com.exedosoft.plat.ui.jquery.grid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author aa
 */
public class GridListImageGallery extends GridList {

	private static Log log = LogFactory.getLog(GridListImageGallery.class);

	public GridListImageGallery() {
		
		dealTemplatePath("/grid/GridListImageGallery.ftl");
	}

}

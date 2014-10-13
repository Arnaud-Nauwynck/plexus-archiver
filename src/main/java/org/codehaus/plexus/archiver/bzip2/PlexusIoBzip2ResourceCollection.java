package org.codehaus.plexus.archiver.bzip2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.apache.maven.shared.utils.io.IOUtil;
import org.codehaus.plexus.components.io.attributes.Java7FileAttributes;
import org.codehaus.plexus.components.io.attributes.PlexusIoResourceAttributes;
import org.codehaus.plexus.components.io.resources.PlexusIoCompressedFileResourceCollection;
import org.codehaus.plexus.components.io.resources.PlexusIoResourceCollection;


/**
 * Implementation of {@link PlexusIoResourceCollection} for
 * bzip2 compressed files.
 */
public class PlexusIoBzip2ResourceCollection
    extends PlexusIoCompressedFileResourceCollection
{
    protected InputStream getInputStream( File file )
        throws IOException
    {
        InputStream fis = new FileInputStream( file );
        try
        {
            final InputStream result = BZip2UnArchiver.getBZip2InputStream( fis );
            if ( result == null )
            {
                throw new IOException( file.getPath()
                                       + " is an invalid bzip2 file. " );
            }
            fis = null;
            return result;
        }
        finally
        {
            IOUtil.close( fis );
        }
    }


	@Override protected PlexusIoResourceAttributes getAttributes(File file) throws IOException {
        return new Java7FileAttributes(file, new HashMap<Integer, String>(), new HashMap<Integer, String>());
	}

	protected String getDefaultExtension()
    {
        return ".bz2";
    }
}

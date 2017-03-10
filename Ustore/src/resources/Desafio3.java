package resources;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("upload")
public class Desafio3 
{
	@POST
	@Path("/upFile")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream, @FormDataParam("file") FormDataContentDisposition fileDetail) 
	{
		String uploadedFileLocation = "d://uploads/" + fileDetail.getFileName();

		writeToFile(uploadedInputStream, uploadedFileLocation);

		String output = "Arquivo criado em: " + uploadedFileLocation;

		return Response.status(200).entity(output).build();
	}

	private void writeToFile(InputStream uploadedInputStream, String uploadLocation) 
	{
		try 
		{
			File file = new File(uploadLocation);
			file.getParentFile().mkdirs();
			
			int read = 0;
			byte[] bytes = new byte[1024];
			OutputStream out = new FileOutputStream(new File(uploadLocation));

			out = new FileOutputStream(new File(uploadLocation));
			
			while ((read = uploadedInputStream.read(bytes)) != -1) 
			{
				out.write(bytes, 0, read);
			}
			
			out.flush();
			out.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
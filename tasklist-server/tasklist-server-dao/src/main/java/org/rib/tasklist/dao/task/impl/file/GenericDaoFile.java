package org.rib.tasklist.dao.task.impl.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTimeUtils;
import org.rib.tasklist.api.ManagedItem;
import org.rib.tasklist.ctrl.TasklistException;
import org.rib.tasklist.dao.task.GenericDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.thoughtworks.xstream.XStream;

public class GenericDaoFile<T extends ManagedItem> implements GenericDao<T> {

	final Logger logger = LoggerFactory.getLogger("org.rib.tasklist.dao.task.impl.file.GenericDaoFile");
	
	private final Path rootPath;
	private static long idx = DateTimeUtils.currentTimeMillis();

	
	public GenericDaoFile(Path rootPath) {
	    Assert.notNull(rootPath);
			    	    
		this.rootPath = rootPath.toAbsolutePath().normalize();
		this.logger.info("Using rootPath={}", this.rootPath);
		
		if (!Files.exists(this.rootPath)) {
			throw new TasklistException("Given rootPath does not exists, rootPath=" + this.rootPath);
		}		
		// we do not check for permissions and so on ....
	}

	@Override
	public T create(T item) {
		if (item == null) {
			throw new TasklistException("Given item was null!");
		}
		item.setId(createId());
		write(item);
		return item;		
	}


	@Override
	public void delete(String id) {
		Path toDelete = this.rootPath.resolve(id);
		if (Files.exists(toDelete)) {
			try {
			  Files.delete(toDelete);
			} catch (IOException ioEx) {
			  throw new TasklistException("Failed to delete item with id=" + id, ioEx);
			}
		}		
	}


	@Override
	public T update(T item) {
		Path toUpdate = this.rootPath.resolve(item.getId());
		if (!Files.exists(toUpdate)) {
	      String msg = String.format("Can not update given item, item not found, id=%s, rootPath=%s", item.getId(), this.rootPath);
		  throw new TasklistException(msg);
		}		
		write(item);
		return item;
	}

	@Override
	public boolean exists(String id) {
		Path lookUp = this.rootPath.resolve(id).toAbsolutePath().normalize();
		return Files.exists(lookUp);
	}

	@Override
	public T get(String id) {
		Path lookUp = this.rootPath.resolve(id).toAbsolutePath().normalize();
    	return read(lookUp);
	}


	@Override
	public List<T> getAll() {
		List<T> items = new ArrayList<>();
		for (File file : this.rootPath.toFile().listFiles()) {
			items.add(read(file.toPath()));
		}
		return items;
	}

	protected void write(T item)  {
		Path path = this.rootPath.resolve(item.getId());
		XStream xstream = new XStream();
		
		try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
		  String xml = xstream.toXML(item);
		  writer.write(xml);	
		} catch (IOException ioEx) {
		  String msg = String.format("Can not write given item, id=%s, rootPath=%s", item.getId(), this.rootPath);	
		  throw new TasklistException(msg, ioEx);
		}
	}
	
	@SuppressWarnings("unchecked")
	protected T read(Path lookUp)  {
		XStream xstream = new XStream();
		try (BufferedReader reader = Files.newBufferedReader(lookUp, StandardCharsets.UTF_8)) {			
		  return (T) xstream.fromXML(reader);
		} catch (IOException ex) {
		  String msg = String.format("Can not read item, lookUp=%s", lookUp);
		  throw new TasklistException(msg, ex);
		}
	}
	
	protected String createId() {
		return "" + idx++;		
	}
}

package tim3.spring.project.isamrs.service;

import java.util.List;

import tim3.spring.project.isamrs.model.Destination;

public interface DestinationService {
	public Destination getOne(long id);
	public List<Destination> getAll();
	public Destination create(Destination destination);
	public Destination update(Destination destination);
	public void delete(long id);
	public Destination save(Destination destination);

}
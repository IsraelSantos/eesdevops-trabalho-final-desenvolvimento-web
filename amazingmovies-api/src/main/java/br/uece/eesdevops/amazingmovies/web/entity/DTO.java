package br.uece.eesdevops.amazingmovies.web.entity;

public interface DTO<T,D> {
	public T toDomain();
	public D toDTO(T entity);
}

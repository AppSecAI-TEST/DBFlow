package com.raizlabs.android.dbflow.structure.container;

import com.raizlabs.android.dbflow.sql.SqlUtils;
import com.raizlabs.android.dbflow.structure.InternalAdapter;
import com.raizlabs.android.dbflow.structure.Model;
import com.raizlabs.android.dbflow.structure.RetrievalAdapter;

/**
 * Description: The base class that generated {@link com.raizlabs.android.dbflow.structure.container.ContainerAdapter} implement
 * to provide the necessary interactions.
 */
public abstract class ContainerAdapter<ModelClass extends Model> implements InternalAdapter<ModelClass, ModelContainer<ModelClass, ?>>, RetrievalAdapter<ModelClass, ModelContainer<ModelClass, ?>> {

    /**
     * Saves the container to the DB.
     *
     * @param modelContainer The container to read data from into {@link android.content.ContentValues}
     */
    @Override
    public void save(ModelContainer<ModelClass, ?> modelContainer) {
        SqlUtils.save(modelContainer, this, modelContainer.getModelAdapter());
    }

    /**
     * Inserts the specified model into the DB.
     *
     * @param modelContainer The model container to insert.
     */
    public void insert(ModelContainer<ModelClass, ?> modelContainer) {
        SqlUtils.insert(modelContainer, this, modelContainer.getModelAdapter());
    }

    /**
     * Updates the specified model into the DB.
     *
     * @param modelContainer The model to update.
     */
    public void update(ModelContainer<ModelClass, ?> modelContainer) {
        SqlUtils.update(modelContainer, this, modelContainer.getModelAdapter());
    }

    /**
     * Deletes the specified container using the primary key values contained in it.
     *
     * @param modelContainer The container to delete.
     */
    @Override
    public void delete(ModelContainer<ModelClass, ?> modelContainer) {
        SqlUtils.delete(modelContainer, this);
    }

    /**
     * Converts the container into a {@link ModelClass}
     *
     * @param modelContainer The container to read data from into a {@link ModelClass}
     * @return a new model instance.
     */
    public abstract ModelClass toModel(ModelContainer<ModelClass, ?> modelContainer);

    /**
     * If a {@link com.raizlabs.android.dbflow.structure.Model} has an autoincrementing primary key, then
     * this method will be overridden.
     *
     * @param modelContainer The model container object to store the key
     * @param id             The key to store
     */
    @Override
    public void updateAutoIncrement(ModelContainer<ModelClass, ?> modelContainer, long id) {

    }

    /**
     * @param modelContainer The model container object to read primary key from
     * @return The value of the {@link com.raizlabs.android.dbflow.annotation.Column#PRIMARY_KEY_AUTO_INCREMENT} if there is one.
     */
    @Override
    public long getAutoIncrementingId(ModelContainer<ModelClass, ?> modelContainer) {
        return 0;
    }

    /**
     * @param model The model to retrieve the caching id from.
     * @return The id that comes from the model. This is generated by subclasses of this adapter.
     */
    @Override
    public long getCachingId(ModelContainer<ModelClass, ?> model) {
        return getAutoIncrementingId(model);
    }

    /**
     * Returns the type of the column for this model container. It's useful for when we do not know the exact class of the column
     * when in a {@link com.raizlabs.android.dbflow.structure.container.ModelContainer}
     *
     * @param columnName The name of the column to look up
     * @return The class that corresponds to the specified columnName
     */
    public abstract Class<?> getClassForColumn(String columnName);

}

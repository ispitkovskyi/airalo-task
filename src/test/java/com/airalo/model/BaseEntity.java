package com.airalo.model;

import com.fasterxml.jackson.annotation.*;

import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseEntity {

    @JsonIgnore
    public Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public BaseEntity(){

    }

/*
    //MEMO: It automatically makes casting to type of respective field
    public <T extends BaseEntity> BaseEntity(HashMap<String, Object> nameValueMap) throws Exception {
        if(nameValueMap==null)
            throw new Exception("No name-value map was provided to create Entity instance");

        getEntityFieldsAndSetValues(this, nameValueMap);
    }*/

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void setAdditionalProperties(HashMap<String, Object> props) {
        if(props!=null)
            props.entrySet().stream().forEach(propEntry -> this.additionalProperties.put(propEntry.getKey(), propEntry.getValue()));
    }
/*
     *//**
     *
     * @param entityType - Type of entity you need to create
     * @param nameValueMap - map with ParamenterName - ParameterValue pairs, where each ParameterName must match name of property,
     *                     specified in JsonProperty annotation in corresponding Entity-class
     * @param <T>
     * @return - Returns instance of Entity class, of type passed as 'entityType' argument, where each class-variable
     *          annotated with @JsonProperty with annotation-value equal to ParameterName, has value matching ParameterValue
     *          has value
     * @throws Exception
     *//*
    public static <T extends BaseEntity> T newInstance(Class<T> entityType, HashMap<String, Object> nameValueMap) throws Exception {
        if(nameValueMap==null)
            throw new Exception("No name-value map was provided to create Entity instance");

        if(entityType==null)
            entityType = (Class<T>) BaseEntity.class;

        T entityInstance = entityType.getDeclaredConstructor().newInstance();
        getEntityFieldsAndSetValues(entityInstance, nameValueMap);
        return entityInstance;
    }

    public static void getEntityFields(List<Field> fields, Class<?> entityType){
        getEntityFields(fields, entityType, true);
    }

    public static void getEntityFields(List<Field> fields, Class<?> entityType, boolean includeSuperClass){
        fields.addAll(Arrays.asList(entityType.getDeclaredFields()));
        Class superclass = entityType.getSuperclass();
        if(superclass != null && includeSuperClass){
            getEntityFields(fields, superclass, true);
        }
    }

    public static Field getFieldByAnnotationValue(List<Field> objFields, String annotationValue){
        return objFields.stream()
                .filter(field -> field.isAnnotationPresent(JsonProperty.class))
                .filter(field -> field.getAnnotation(JsonProperty.class).value().equals(annotationValue))
                .findFirst().orElse(null);
    }

    public void updateFields(HashMap<String, Object> nameValueMap) throws Exception {
        if(nameValueMap==null)
            return;
        getEntityFieldsAndSetValues(this, nameValueMap);
    }

    private static <T extends BaseEntity> void getEntityFieldsAndSetValues(T entityInstance, HashMap<String, Object> fieldValuesMap) throws Exception {
        List<Field> entityFields = new LinkedList<>();
        getEntityFields(entityFields, entityInstance.getClass());
        setEntityFields(entityInstance, entityFields, fieldValuesMap);
    }
    private static <T extends BaseEntity> void setEntityFields(T entityInstance, List<Field> entityFields, HashMap<String, Object> fieldValuesMap) throws Exception {
        List<String> errors = new LinkedList<>();

        fieldValuesMap.entrySet().stream().forEach(entry -> {
            String fieldName = entry.getKey();
            Object fieldValue = entry.getValue();
            Field fld = getFieldByAnnotationValue(entityFields, fieldName);
            if (fld != null) {
                String fldType = fld.getType().getSimpleName();
                if(StringUtils.isEmpty(fieldValue + ""))
                    if(!fldType.equals("String"))
                        fieldValue = null;

                try {
                    fld.set(entityInstance, fieldValue);
                } catch (IllegalAccessException ex) {
                    errors.add(String.format("IllegalAccessException occurred on attempt to set field %s", fieldName));
                }
            }else{
                entityInstance.setAdditionalProperty(fieldName, fieldValue);
            }
        });

        if(errors.size()>0)
            throw new Exception("Error(s) on attempts to set fields of Entity instance, using reflection:\n" + StringUtils.join(errors, ",\n"));
    }*/

/*    @Override
    public String toString(){
        List<Field> entityFields = new LinkedList<>();
        getEntityFields(entityFields, this.getClass());
        ToStringBuilder sb = new ToStringBuilder(this);
        entityFields.stream()
                .filter(field -> field.canAccess(this))
                .forEach(field -> {
                    String fieldName = field.getName();
                    Object fieldValue = null;
                    try{
                        fieldValue = field.get(this);
                    }catch (IllegalAccessException ex){
                        TestLogger.logDebug(String.format("Entity-toString: Could not get value of %s field", fieldName));
                    }
                    sb.append(fieldName, fieldValue);
                });
        return sb.toString();
    };

    @Override
    public int hashCode() {
        List<Field> entityFields = new LinkedList<>();
        getEntityFields(entityFields, this.getClass());
        HashCodeBuilder hcb = new HashCodeBuilder();
        entityFields.stream()
                .filter(field -> field.canAccess(this))
                .forEach(field -> {
                    try{
                        hcb.append(field.get(this));
                    }catch (IllegalAccessException ex){
                        TestLogger.logDebug(String.format("Entity-hashSum: Could not get value of %s field", field.getName()));
                    }
                });
        return hcb.toHashCode();
    }*/

/*    public <T extends Entity> boolean equalize(T expected){
        return additionalPropertiesEquals((HashMap<String, Object>) expected.getAdditionalProperties());
    }

    public boolean additionalPropertiesEquals(HashMap<String, Object> expectedAdditionalProperties) {
        boolean result = true;

        if(expectedAdditionalProperties != null && this.additionalProperties == null)
            return false;

        if(expectedAdditionalProperties != null) {
            Iterator itr = expectedAdditionalProperties.entrySet().iterator();
            while (itr.hasNext() && result) {
                Map.Entry<String, Object> expectedEntry = (Map.Entry<String, Object>) itr.next();
                String key = expectedEntry.getKey();
                if (!this.getAdditionalProperties().containsKey(key)) {
                    result = false;
                } else {
                    this.getAdditionalProperties().get(key).equals(expectedEntry.getValue());
                }
            }
        }

        return result;
    }

    *//**
     * By default entities from two Lists will be matched by "name"
     * @param expected
     * @param actual
     * @param jsonPropertyName
     * @return
     *//*
    protected boolean compare(Object expected, Object actual, String jsonPropertyName){
        return compare(expected, actual, jsonPropertyName, (entity) -> entity.getName());
    }

    *//**
     * It's possible to match entities from two Lists, using a custom "matcher" function, which should be passed here s listItemsMatcher parameter
     * @param expected
     * @param actual
     * @param jsonPropertyName
     * @param listItemsMatcher - function which implements finding value of property, which should match between two Entities taken from ACTUAL and EXPECTED lists.
     *                        Entities are extracted from ACTUAL/EXPECTED lists by this matcher and then compared to each other for equality
     * @return
     *//*
    protected boolean compare(Object expected, Object actual, String jsonPropertyName,  Function<Entity, String> listItemsMatcher){
        if(expected==null)
            return true;
        if(actual==null){
            return false;
        }

        boolean equal;
        if(actual instanceof List) {
            equal = assertEntityLists((List) actual, (List) expected, listItemsMatcher);
        }else{
            equal = (actual instanceof Entity) ? ((Entity)actual).equalize((Entity)expected) : actual.equals(expected);
        }
        if(!equal) {
            if(expected instanceof Entity){
            }else{
            }
        }
        return equal;
    }

    protected boolean assertEntityLists(List actualEntities, List expectedEntities, Function<Entity, String> entityIdentifier){
        List<Boolean>results = new ArrayList();

        for(Object expected : expectedEntities){
            Boolean success;
            String error;
            if(expected instanceof Entity) {
                Entity expEnt = (Entity)expected;
                String expEntityIdentifier = entityIdentifier.apply(expEnt);
                List<Entity> matchingEntity = (List<Entity>) actualEntities.stream().filter(actual -> entityIdentifier.apply((Entity) actual).equals(expEntityIdentifier)).collect(Collectors.toList());
                if (matchingEntity.size() != 1) {
                    if(expEntityIdentifier == null) {
                        continue;
                    }else {
                        return false;
                    }
                }
                Entity actualEnt = matchingEntity.get(0);
                success = actualEnt.equalize(expEnt);
                error =  String.format("Actual entity %s is different from expected %s", entityIdentifier.apply(actualEnt) , expEntityIdentifier);
            }else if(expected instanceof String){
                success = actualEntities.contains(expected);
                error = String.format("Actual array of String values does not contain expected one: #s", expected);
            }else{
                success = false;
                error = "Not supported type for assertion/comparison of lists";
            }
            if (!success)
            results.add(success);
        }

        return !results.contains(Boolean.FALSE);
    }*/
}

// v1 IOvuViewService.aidl
package com.sleekbit.ovuview.service.v1;

// we have to declare import statements even of our own package
import com.sleekbit.ovuview.service.v1.DataSetInfo;
import com.sleekbit.ovuview.service.v1.TemperatureSymptomValue;
import com.sleekbit.ovuview.service.v1.BloodPressureSymptomValue;
import com.sleekbit.ovuview.service.v1.WeightSymptomValue;
import com.sleekbit.ovuview.service.v1.ResultStatus;


/**
 * OvuView bound remote service.
 */
interface IOvuViewService {

    /**
     * @return all configured owned datasets on this device
     */
    List<DataSetInfo> getOwnedDataSets(out ResultStatus resultStatus);

    ///////////////////////////////////////////////////////////////////////////////////////////
    //
    // dataset methods
    //

    /**
     * Set the builtin temperature symptom.
     *
     * @param datasetId identifies the dataset
     * @param date identifies date, as provided by OvuUtil
     * @param value encloses complete information about the value to be set
     * @param resultStatus result status of the operation (contains status code and futher info)
     */
    void setTemperature(String dataSetId, int date, in TemperatureSymptomValue value, out ResultStatus resultStatus);

    /**
     * Retrieve temperature symptom value on the given date.
     *
     * @param datasetId identifies the dataset
     * @param date identifies date, as provided by OvuUtil
     * @param resultStatus result status of the operation (contains status code and futher info)
     * @return complete information about temperature symptom
     */
    TemperatureSymptomValue getTemperature(String dataSetId, int date, out ResultStatus resultStatus);

    /**
     * Set blood pressure symptom.
     *
     * @param datasetId identifies the dataset
     * @param date identifies date, as provided by OvuUtil
     * @param value encloses complete information about the value to be set
     * @param resultStatus result status of the operation (contains status code and futher info)
     */
    void setBloodPressure(String dataSetId, int date, in BloodPressureSymptomValue value, out ResultStatus resultStatus);

    /**
     * Retrieve blood pressure symptom value.
     *
     * @param datasetId identifies the dataset
     * @param date identifies date, as provided by OvuUtil
     * @param resultStatus result status of the operation (contains status code and futher info)
     * @return complete information about blood pressure symptom
     */
    BloodPressureSymptomValue getBloodPressure(String dataSetId, int date, out ResultStatus resultStatus);

    /**
     * Set weight symptom value.
     *
     * @param datasetId identifies the dataset
     * @param date identifies date, as provided by OvuUtil
     * @param weight weight symptom value along with origin app id
     * @param resultStatus result status of the operation (contains status code and futher info)
     */
    void setWeight(String dataSetId, int date, in WeightSymptomValue weight, out ResultStatus resultStatus);

    /**
     * Retrieve weight symptom value.
     *
     * @param datasetId identifies the dataset
     * @param date identifies date, as provided by OvuUtil
     * @param resultStatus result status of the operation (contains status code and futher info)
     * @return weight symptom value
     */
    WeightSymptomValue getWeight(String dataSetId, int date, out ResultStatus resultStatus);

}

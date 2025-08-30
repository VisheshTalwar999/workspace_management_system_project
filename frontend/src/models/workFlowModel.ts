export interface WorkflowModel {
    fieldLabel: string,
    fieldName: string,
    fieldType: string,
    fieldUniqueKey: string
    options: string[],
    value: string | string[] | boolean
}
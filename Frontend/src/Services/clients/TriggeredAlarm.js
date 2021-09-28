import { BaseClient } from "../api/BaseClient";

class TriggeredAlarmExecutor extends BaseClient {
  constructor() {
    super("triggered-alarms");
  }
}

class SaveTriggeredAlarmExecutor extends BaseClient {
  constructor() {
    super("triggered-alarms/salvar");
  }
}


export const getAllTriggeredAlarm = async () => {
  return new TriggeredAlarmExecutor().getAll();
};

export const getTriggeredAlarm = async (id) => {
  return new TriggeredAlarmExecutor().get(id);
};

export const saveTriggeredAlarm = async (source) => {
  return new SaveTriggeredAlarmExecutor().post(source);
};

export const updateTriggeredAlarm = async (source) => {
  return new TriggeredAlarmExecutor().put(source);
};

export const deleteTriggeredAlarm = async (id) => {
  return new TriggeredAlarmExecutor().delete(id);
};

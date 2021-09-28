import { BaseClient } from "../api/BaseClient";

class AlarmExecutor extends BaseClient {
  constructor() {
    super("alarms");
  }
}

class SaveAlarmExecutor extends BaseClient {
  constructor() {
    super("alarms/salvar");
  }
}

class MostAlarmExecutor extends BaseClient {
  constructor() {
    super("alarms/mostTriggeredAlarms");
  }
}



export const getAlarms = async () => {
  return new AlarmExecutor().getAll();
};

export const getMostAlarms = async () => {
  return new MostAlarmExecutor().getAll();
};

export const getAlarmsDescription = async (descricao) => {
  return new AlarmExecutor().getDescription(descricao);
};

export const saveAlarms = async (source) => {
  return new SaveAlarmExecutor().post(source);
};

export const updateAlarms = async (source) => {
  return new AlarmExecutor().put(source);
};

export const deleteAlarms = async (id) => {
  return new AlarmExecutor().delete(id);
};


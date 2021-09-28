import { BaseClient } from "../api/BaseClient";

class EquipExecutor extends BaseClient {
  constructor() {
    super("equipments");
  }
}

class SaveEquipExecutor extends BaseClient {
  constructor() {
    super("equipments/salvar");
  }
}

export const getAllEquips = async () => {
  return new EquipExecutor().getAll();
};

export const getEquips = async (id) => {
  return new EquipExecutor().get(id);
};

export const saveEquips = async (source) => {
  return new SaveEquipExecutor().post(source);
};

export const updateEquips = async (source) => {
  return new EquipExecutor().put(source);
};

export const deleteEquips = async (id) => {
  return new EquipExecutor().delete(id);
};

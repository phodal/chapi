declare namespace arkts.contracts {
  interface Configuration {
    readonly name: string;
    enabled?: boolean;
  }
}

export declare class AmbientService {
  readonly name: string;
  constructor(name: string);
  run(input?: string): Promise<string>;
}

export declare function createService(name: string): AmbientService;

import './App.css';
import { Card, TextField, Button } from "@mui/material";
import * as React from "react";


function App() {
  return (
      <div className="App">
          <header className="App-header">
              <h1>Binance Klines</h1>
          </header>
          <main className="">
              <Card
                  className="flex flex-col gap-8 justify-center items-center mx-auto my-12 p-12"
                  sx={{ width: "500px" }}>
                  <TextField
                      id="standard-basic"
                      label="Symbol"
                      type="string"
                      variant="standard"
                  />
                  <TextField
                      id="datetime-local"
                      label="Start Date"
                      type="datetime-local"
                      defaultValue="2017-05-24T10:30"
                      sx={{ width: 250 }}
                      InputLabelProps={{
                          shrink: true,
                      }}
                  />
                  <TextField
                      id="datetime-local"
                      label="End Date"
                      type="datetime-local"
                      defaultValue="2017-05-24T10:30"
                      sx={{ width: 250 }}
                      InputLabelProps={{
                          shrink: true,
                      }}
                  />
                  <TextField
                      id="standard-basic"
                      label="Run Name"
                      variant="standard"
                  />
                  <Button variant="contained">Submit</Button>
              </Card>
          </main>
      </div>
  );
}

export default App;

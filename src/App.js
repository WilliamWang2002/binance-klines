import "./App.css";
import { Card, TextField, Button } from "@mui/material";
import * as React from "react";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import Select from "@mui/material/Select";



function App() {
    const [startTime, setStartTime] = React.useState("");
    const [endTime, setEndTime] = React.useState("");
    const [symbol, setSymbol] = React.useState("");
    const [runName, setRunName] = React.useState("");
    const [frequency, setFrequency] = React.useState("");
    const axios = require("axios");

    function onClick() {
        let url = "";
        if (frequency === "") {
            url = `http://localhost:8888/loadKlineData?symbol=${symbol}&startingTime=${startTime}&endingTime=${endTime}&runName=${runName}&frequency=`;
        } else {
            url = `http://localhost:8888/loadKlineData?symbol=${symbol}&startingTime=${startTime}&endingTime=${endTime}&runName=${runName}&frequency=${frequency}`;
        }
        console.log(url);
        axios
            .post(url)
            .then(function (response) {
                console.log(response);
                alert("All data stored to the database");
            })
            .catch(function (error) {
                console.log(error);
                if (error.response.status === 409) {
                    alert("Run Name already exists in database");
                } else {
                    alert(error.response.message);
                }
            });
    }

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
                        onChange={(e) => setSymbol(e.currentTarget.value)}
                    />
                    <TextField
                        id="datetime-local"
                        label="Start Date"
                        type="datetime-local"
                        sx={{ width: 250 }}
                        InputLabelProps={{
                            shrink: true,
                        }}
                        onChange={(e) => {
                            let timeStamp = Math.floor(
                                new Date(e.currentTarget.value).getTime()
                            );
                            console.log(timeStamp);
                            setStartTime(timeStamp);
                        }}
                    />
                    <TextField
                        id="datetime-local"
                        label="End Date"
                        type="datetime-local"
                        sx={{ width: 250 }}
                        InputLabelProps={{
                            shrink: true,
                        }}
                        onChange={(e) => {
                            let timeStamp = Math.floor(
                                new Date(e.currentTarget.value).getTime()
                            );
                            console.log(timeStamp);
                            setEndTime(timeStamp);
                        }}
                    />
                    <TextField
                        id="standard-basic"
                        label="Run Name"
                        variant="standard"
                        onChange={(e) => setRunName(e.currentTarget.value)}
                    />
                    <FormControl
                        variant="standard"
                        sx={{ m: 1, minWidth: 200 }}>
                        <InputLabel id="demo-simple-select-standard-label">
                            Frequency
                        </InputLabel>
                        <Select
                            labelId="demo-simple-select-standard-label"
                            id="demo-simple-select-standard"
                            value={frequency}
                            onChange={(e) => setFrequency(e.target.value)}
                            label="Rolling Up Amount">
                            <MenuItem value="">
                                <em>None</em>
                            </MenuItem>
                            <MenuItem value={"ONE_DAY"}>Day</MenuItem>
                            <MenuItem value={"ONE_HOUR"}>Hour</MenuItem>
                        </Select>
                    </FormControl>

                    <Button variant="contained" onClick={onClick}>
                        Run
                    </Button>
                </Card>
            </main>
        </div>
    );
}

export default App;

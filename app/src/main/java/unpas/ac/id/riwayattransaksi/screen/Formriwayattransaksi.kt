package unpas.ac.id.riwayattransaksi.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benasher44.uuid.uuid4
import kotlinx.coroutines.launch
import unpas.ac.id.riwattransaksi.ui.theme.Purple700
import unpas.ac.id.riwattransaksi.ui.theme.Teal200
import unpas.ac.id.riwayattransaksi.model.RiwayatTransaksi
import unpas.ac.id.riwayattransaksi.persistances.RiwayatTransaksiDao

@Composable
fun Formriwayattransaksi(riwayatTransaksiDao:RiwayatTransaksiDao) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val norekening = remember { mutableStateOf(TextFieldValue("")) }
    val jenistransaksi = remember { mutableStateOf(TextFieldValue("")) }
    val tanggaltransaksi = remember { mutableStateOf(TextFieldValue("")) }
    val nama = remember { mutableStateOf(TextFieldValue("")) }



    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        Text(text = "Riwayat Transaksi Keuangan", style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
            ,modifier = Modifier.padding(2.dp))
        OutlinedTextField(
            label = { Text(text = "No Rekening") },
            value = norekening.value,
            onValueChange = {
                norekening.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType =
            KeyboardType.Decimal),
            placeholder = { Text(text = "XXXXX") }
        )
        OutlinedTextField(
            label = { Text(text = "Jenis Transaksi") },
            value = jenistransaksi.value,
            onValueChange = {
                jenistransaksi.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            placeholder = { Text(text = "XXXX") }
        )
        OutlinedTextField(
            label = { Text(text = "Tanggal Transaksi") },
            value = tanggaltransaksi.value,
            onValueChange = {
                tanggaltransaksi.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            placeholder = { Text(text = "DD-MM-YYYY") }
        )
        OutlinedTextField(
            label = { Text(text = "Nama") },
            value = nama.value,
            onValueChange = {
                nama.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            placeholder = { Text(text = "XXXXX") }
        )
        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )
        Row (modifier = Modifier.padding(4.dp).fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {

                if(norekening.value.text !== "" && jenistransaksi.value.text !== ""){
                    if (tanggaltransaksi.value.text !== "" && nama.value.text !== "" ){
                        val id = uuid4().toString()
                        val item = RiwayatTransaksi(id, norekening.value.text,
                        jenistransaksi.value.text,tanggaltransaksi.value.text, nama.value.text)
                        scope.launch {
                            riwayatTransaksiDao.insertAll(item)
                        }
                        tanggaltransaksi.value = TextFieldValue("")
                        jenistransaksi.value = TextFieldValue("")
                       norekening.value = TextFieldValue("")
                        nama.value = TextFieldValue("")
                    }else{
                        Toast.makeText(context, "Tanggal Dan Jenis Transaksi Harus di Isi",
                            Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(context, "Tanggal dan Nama Harus di Isi",
                        Toast.LENGTH_LONG).show()
                }



            }, colors = loginButtonColors) {
                Text(
                    text = "Simpan",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp,
                    ), modifier = Modifier.padding(8.dp)
                )
            }
            Button(modifier = Modifier.weight(5f), onClick = {
                tanggaltransaksi.value = TextFieldValue("")
                nama.value = TextFieldValue("")
                norekening.value = TextFieldValue("")
                jenistransaksi.value = TextFieldValue("")
            }, colors = resetButtonColors) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
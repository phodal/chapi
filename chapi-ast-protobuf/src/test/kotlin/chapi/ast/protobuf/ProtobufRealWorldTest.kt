package chapi.ast.protobuf

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class ProtobufRealWorldTest {
    @Test
    fun `should parse all field an message`() {
        @Language("protobuf")
        val protobufCode = """
syntax = "proto3";

import "github.com/gogo/protobuf/gogoproto/gogo.proto";

package bbq.interface.bullet.v1;

option go_package = "api";
option (gogoproto.goproto_getters_all) = false;

message DataReport {
	string app = 1 [(gogoproto.jsontag) = "mobi_app", (gogoproto.moretags)='form:"mobi_app"'];
	string client = 2 [(gogoproto.jsontag) = "platform", (gogoproto.moretags)='form:"platform"'];
	string version = 3 [(gogoproto.jsontag) = "version", (gogoproto.moretags)='form:"version"'];
	string channel = 4 [(gogoproto.jsontag) = "channel", (gogoproto.moretags)='form:"channel"'];
	string location = 5 [(gogoproto.jsontag) = "location", (gogoproto.moretags)='form:"location"'];
	string query_id = 6 [(gogoproto.jsontag) = "query_id", (gogoproto.moretags)='form:"query_id"'];
	string buvid = 7 [(gogoproto.jsontag) = "buvid", (gogoproto.moretags)='form:"buvid"'];
	int64 svid = 8 [(gogoproto.jsontag) = "svid", (gogoproto.moretags)='form:"oid"'];
	int32 total_duration = 9 [(gogoproto.jsontag) = "total_duration", (gogoproto.moretags)='form:"total_duration"'];
	int32 play_duration = 10 [(gogoproto.jsontag) = "duration", (gogoproto.moretags)='form:"duration"'];
	int32 data_type = 11 [(gogoproto.jsontag) = "data_type", (gogoproto.moretags)='form:"data_type"'];
	int32 page = 12 [(gogoproto.jsontag) = "page_id", (gogoproto.moretags)='form:"page_id"'];
	int32 module = 13 [(gogoproto.jsontag) = "module_id", (gogoproto.moretags)='form:"module_id"'];
}

message ListBulletReq {
	int64 	oid 			= 1 [(gogoproto.moretags)='form:"oid"'];
	int32 	start_ms 		= 2 [(gogoproto.moretags)='form:"start_ms"'];
	int32 	end_ms 			= 3 [(gogoproto.moretags)='form:"end_ms"'];
	string 	cursor_next 	= 4 [(gogoproto.moretags)='form:"cursor_next"'];
	int64 	mid 			= 5;
}

message Bullet {
	int64 	id 				= 1;
	int64 	oid				= 2[(gogoproto.moretags)='form:"oid"'];
	int64 	mid 			= 3[(gogoproto.jsontag) = "mid", (gogoproto.moretags)='form:"mid"'];
	int32 	offset_ms 		= 4[(gogoproto.jsontag) = "offset_ms", (gogoproto.moretags)='form:"offset_ms"'];
	int32 	offset 			= 5;
	string 	content 		= 6[(gogoproto.jsontag) = "content", (gogoproto.moretags)='form:"content"'];
	string 	cursor_value	= 7[(gogoproto.jsontag) = "cursor_value,omitempty"];
}

message ListBulletReply {
	bool 			has_more 	= 1 [(gogoproto.jsontag) = "has_more"];
	repeated Bullet list 		= 2 [(gogoproto.jsontag) = "list,omitempty"];
}

"""
        val filePath = "path/to/file.proto"
        val analyser = ProtobufAnalyser()

        val codeContainer = analyser.analysis(protobufCode, filePath)

        // SHOULD INCLUDE 4 DataStructures
        assertEquals(4, codeContainer.DataStructures.size)

        // first DataStructure include 13 fields
        assertEquals(13, codeContainer.DataStructures[0].Fields.size)

        // second DataStructure include 5 fields
        assertEquals(5, codeContainer.DataStructures[1].Fields.size)

        // third DataStructure include 7 fields
        assertEquals(7, codeContainer.DataStructures[2].Fields.size)

        // fourth DataStructure include 2 fields
        assertEquals(2, codeContainer.DataStructures[3].Fields.size)
    }


    /// load resource/gpt-4o-user.proto and parse it
    @Test
    fun `should parse gpt-4o-user proto and return a CodeContainer`() {
        // Given
        val filepath = this.javaClass.classLoader.getResource("gpt-4o-user.proto")!!.file
        val protobufCode = File(filepath).readText()
        val analyser = ProtobufAnalyser()

        // When
        val codeContainer = analyser.analysis(protobufCode, filepath)

       // write to json
        File("gpt-proto.json").writeText(Json.encodeToString(codeContainer))
    }

    /// should parse proto2
    @Test
    fun `should parse proto2 and return a CodeContainer`() {
        // Given
        @Language("protobuf")
        val protobufCode = """
syntax = "proto2";

package treelite;

message Model {
  repeated Tree trees = 1;
  optional int32 num_feature = 2;
  optional int32 num_output_group = 3;   // >1 for multi-class classification;
                                         // =1 for everything else
  optional bool random_forest_flag = 4;  // true for random forest
                                         // false for gradient boosted trees
  map<string, string> extra_params = 5;  // extra parameters
}
            """
        val filePath = "path/to/file.proto"
        val analyser = ProtobufAnalyser()

        // When
        val codeContainer = analyser.analysis(protobufCode, filePath)

        // Then
        assertEquals("treelite", codeContainer.PackageName)
        assertEquals(1, codeContainer.DataStructures.size)
    }
}

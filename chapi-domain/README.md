# Chapi source code modeling

Goal: source code data model for different language & different language family from [Language support](https://en.wikipedia.org/wiki/First-class_function)

<table class="wikitable" width="100%" style="font-size: 85%">
<tbody><tr>
<th colspan="2" rowspan="2">Language</th>
<th colspan="2"><a href="/wiki/Higher-order_function" title="Higher-order function">Higher-order functions</a></th>
<th colspan="2"><a href="/wiki/Nested_function" title="Nested function">Nested functions</a></th>
<th colspan="2"><a href="/wiki/Non-local_variable" title="Non-local variable">Non-local variables</a></th>
<th rowspan="2" width="25%">Notes
</th></tr>
<tr>
<th>Arguments</th>
<th>Results</th>
<th>Named</th>
<th><a href="/wiki/Anonymous_function" title="Anonymous function">Anonymous</a></th>
<th><a href="/wiki/Closure_(computer_programming)" title="Closure (computer programming)">Closures</a></th>
<th><a href="/wiki/Partial_application" title="Partial application">Partial application</a>
</th></tr>
<tr>
<td rowspan="6">Algol family
</td>
<td><a href="/wiki/ALGOL_60" title="ALGOL 60">ALGOL 60</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#F99;vertical-align:middle;text-align:center;" class="table-no">No</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#F99;vertical-align:middle;text-align:center;" class="table-no">No</td>
<td style="background:#FFB;vertical-align:middle;text-align:center;" class="table-partial">Downwards</td>
<td style="background:#F99;vertical-align:middle;text-align:center;" class="table-no">No</td>
<td rowspan="6">Have <a href="/wiki/Function_type" title="Function type">function types</a>.
</td></tr>
<tr>
<td><a href="/wiki/ALGOL_68" title="ALGOL 68">ALGOL 68</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#FFB;vertical-align:middle;text-align:center;" class="table-partial">Yes<sup id="cite_ref-compa68pascal_8-0" class="reference"><a href="#cite_note-compa68pascal-8">[8]</a></sup></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#FFB;vertical-align:middle;text-align:center;" class="table-partial">Downwards<sup id="cite_ref-9" class="reference"><a href="#cite_note-9">[9]</a></sup></td>
<td style="background:#F99;vertical-align:middle;text-align:center;" class="table-no">No
</td></tr>
<tr>
<td><a href="/wiki/Pascal_(programming_language)" title="Pascal (programming language)">Pascal</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#F99;vertical-align:middle;text-align:center;" class="table-no">No</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#F99;vertical-align:middle;text-align:center;" class="table-no">No</td>
<td style="background:#FFB;vertical-align:middle;text-align:center;" class="table-partial">Downwards</td>
<td style="background:#F99;vertical-align:middle;text-align:center;" class="table-no">No
</td></tr>
<tr>
<td><a href="/wiki/Ada_(programming_language)" title="Ada (programming language)">Ada</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#F99;vertical-align:middle;text-align:center;" class="table-no">No</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#F99;vertical-align:middle;text-align:center;" class="table-no">No</td>
<td style="background:#FFB;vertical-align:middle;text-align:center;" class="table-partial">Downwards</td>
<td style="background:#F99;vertical-align:middle;text-align:center;" class="table-no">No
</td></tr>
<tr>
<td><a href="/wiki/Oberon_(programming_language)" title="Oberon (programming language)">Oberon</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#FFB;vertical-align:middle;text-align:center;" class="table-partial">Non-nested only</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#F99;vertical-align:middle;text-align:center;" class="table-no">No</td>
<td style="background:#FFB;vertical-align:middle;text-align:center;" class="table-partial">Downwards</td>
<td style="background:#F99;vertical-align:middle;text-align:center;" class="table-no">No
</td></tr>
<tr>
<td><a href="/wiki/Delphi_(programming_language)" class="mw-redirect" title="Delphi (programming language)">Delphi</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">2009</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">2009</td>
<td style="background:#F99;vertical-align:middle;text-align:center;" class="table-no">No
</td></tr>
<tr>
<td rowspan="9">C family
</td>
<td><a href="/wiki/C_(programming_language)" title="C (programming language)">C</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#F99;vertical-align:middle;text-align:center;" class="table-no">No</td>
<td style="background:#F99;vertical-align:middle;text-align:center;" class="table-no">No</td>
<td style="background:#F99;vertical-align:middle;text-align:center;" class="table-no">No</td>
<td style="background:#F99;vertical-align:middle;text-align:center;" class="table-no">No</td>
<td>Has <a href="/wiki/Function_pointer" title="Function pointer">function pointers</a>.
</td></tr>
<tr>
<td><a href="/wiki/C%2B%2B" title="C++">C++</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">C++11<sup id="cite_ref-10" class="reference"><a href="#cite_note-10">[10]</a></sup></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">C++11<sup id="cite_ref-doc1968_11-0" class="reference"><a href="#cite_note-doc1968-11">[11]</a></sup></td>
<td style="background:#FFB;vertical-align:middle;text-align:center;" class="table-partial">C++11<sup id="cite_ref-doc1968_11-1" class="reference"><a href="#cite_note-doc1968-11">[11]</a></sup></td>
<td style="background:#FFB;vertical-align:middle;text-align:center;" class="table-partial">C++11</td>
<td>Has function pointers, <a href="/wiki/Function_object" title="Function object">function objects</a>. (Also, see below.)
<p>Explicit partial application possible with <code>std::bind</code>.
</p>
</td></tr>
<tr>
<td><a href="/wiki/C_Sharp_(programming_language)" title="C Sharp (programming language)">C#</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">7</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">2.0 / 3.0</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">2.0</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">3.0</td>
<td>Has <a href="/wiki/Delegate_(CLI)" title="Delegate (CLI)">delegates</a> (2.0) and lambda expressions (3.0).
</td></tr>
<tr>
<td><a href="/wiki/Objective-C" title="Objective-C">Objective-C</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#FFB;vertical-align:middle;text-align:center;" class="table-partial">Using anonymous</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">2.0 + Blocks<sup id="cite_ref-12" class="reference"><a href="#cite_note-12">[12]</a></sup></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">2.0 + Blocks</td>
<td style="background:#F99;vertical-align:middle;text-align:center;" class="table-no">No</td>
<td>Has function pointers.
</td></tr>
<tr>
<td><a href="/wiki/Java_(programming_language)" title="Java (programming language)">Java</a></td>
<td style="background:#FFB;vertical-align:middle;text-align:center;" class="table-partial">Partial</td>
<td style="background:#FFB;vertical-align:middle;text-align:center;" class="table-partial">Partial</td>
<td style="background:#FFB;vertical-align:middle;text-align:center;" class="table-partial">Using anonymous</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Java 8</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Java 8</td>
<td style="background:#F99;vertical-align:middle;text-align:center;" class="table-no">No</td>
<td>Has <a href="/wiki/Anonymous_inner_class" class="mw-redirect" title="Anonymous inner class">anonymous inner classes</a>.
</td></tr>
<tr>
<td><a href="/wiki/Go_(programming_language)" title="Go (programming language)">Go</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#FFB;vertical-align:middle;text-align:center;" class="table-partial">Using anonymous</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes<sup id="cite_ref-13" class="reference"><a href="#cite_note-13">[13]</a></sup></td>
<td>
</td></tr>
<tr>
<td><a href="/wiki/Limbo_(programming_language)" title="Limbo (programming language)">Limbo</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#F99;vertical-align:middle;text-align:center;" class="table-no">No</td>
<td>
</td></tr>
<tr>
<td><a href="/wiki/Newsqueak" title="Newsqueak">Newsqueak</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#F99;vertical-align:middle;text-align:center;" class="table-no">No</td>
<td>
</td></tr>
<tr>
<td><a href="/wiki/Rust_(programming_language)" title="Rust (programming language)">Rust</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#F99;vertical-align:middle;text-align:center;" class="table-no">No</td>
<td>
</td></tr>
<tr>
<td rowspan="9">Functional languages</td>
<td><a href="/wiki/Lisp_(programming_language)" title="Lisp (programming language)">Lisp</a></td>
<td style="background:#FFB;vertical-align:middle;text-align:center;" class="table-partial">Syntax</td>
<td style="background:#FFB;vertical-align:middle;text-align:center;" class="table-partial">Syntax</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#FFB;vertical-align:middle;text-align:center;" class="table-partial">Common Lisp</td>
<td style="background:#F99;vertical-align:middle;text-align:center;" class="table-no">No</td>
<td>(see below)
</td></tr>
<tr>
<td><a href="/wiki/Scheme_(programming_language)" title="Scheme (programming language)">Scheme</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">SRFI 26<sup id="cite_ref-14" class="reference"><a href="#cite_note-14">[14]</a></sup></td>
<td>
</td></tr>
<tr>
<td><a href="/wiki/Julia_(programming_language)" title="Julia (programming language)">Julia</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td>
</td></tr>
<tr>
<td><a href="/wiki/Clojure" title="Clojure">Clojure</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td>
</td></tr>
<tr>
<td><a href="/wiki/ML_(programming_language)" title="ML (programming language)">ML</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td>
</td></tr>
<tr>
<td><a href="/wiki/Haskell_(programming_language)" title="Haskell (programming language)">Haskell</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td>
</td></tr>
<tr>
<td><a href="/wiki/Scala_(programming_language)" title="Scala (programming language)">Scala</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td>
</td></tr>
<tr>
<td><a href="/wiki/F_Sharp_(programming_language)" title="F Sharp (programming language)">F#</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td>
</td></tr>
<tr>
<td><a href="/wiki/OCaml" title="OCaml">OCaml</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td>
</td></tr>
<tr>
<td rowspan="6">Scripting languages
</td>
<td><a href="/wiki/JavaScript" title="JavaScript">JavaScript</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">ECMAScript 5</td>
<td>Partial application possible with user-land code on ES3 <sup id="cite_ref-15" class="reference"><a href="#cite_note-15">[15]</a></sup>
</td></tr>
<tr>
<td><a href="/wiki/Lua_(programming_language)" title="Lua (programming language)">Lua</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes<sup id="cite_ref-16" class="reference"><a href="#cite_note-16">[16]</a></sup></td>
<td>
</td></tr>
<tr>
<td><a href="/wiki/PHP" title="PHP">PHP</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#FFB;vertical-align:middle;text-align:center;" class="table-partial">Using anonymous</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">5.3</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">5.3</td>
<td style="background:#F99;vertical-align:middle;text-align:center;" class="table-no">No</td>
<td>Partial application possible with user-land code.
</td></tr>
<tr>
<td><a href="/wiki/Perl" title="Perl">Perl</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">6</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">6<sup id="cite_ref-17" class="reference"><a href="#cite_note-17">[17]</a></sup></td>
<td>
</td></tr>
<tr>
<td><a href="/wiki/Python_(programming_language)" title="Python (programming language)">Python</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#FFB;vertical-align:middle;text-align:center;" class="table-partial">Expressions only</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">2.5<sup id="cite_ref-18" class="reference"><a href="#cite_note-18">[18]</a></sup></td>
<td>(see below)
</td></tr>
<tr>
<td><a href="/wiki/Ruby_(programming_language)" title="Ruby (programming language)">Ruby</a></td>
<td style="background:#FFB;vertical-align:middle;text-align:center;" class="table-partial">Syntax</td>
<td style="background:#FFB;vertical-align:middle;text-align:center;" class="table-partial">Syntax</td>
<td style="background:#F99;vertical-align:middle;text-align:center;" class="table-no">Unscoped</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#FFB;vertical-align:middle;text-align:center;" class="table-partial">1.9</td>
<td>(see below)
</td></tr>
<tr>
<td rowspan="7">Other languages
</td>
<td><a href="/wiki/Fortran" title="Fortran">Fortran</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#F99;vertical-align:middle;text-align:center;" class="table-no">No</td>
<td style="background:#F99;vertical-align:middle;text-align:center;" class="table-no">No</td>
<td style="background:#F99;vertical-align:middle;text-align:center;" class="table-no">No</td>
<td>
</td></tr>
<tr>
<td><a href="/wiki/Io_(programming_language)" title="Io (programming language)">Io</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#F99;vertical-align:middle;text-align:center;" class="table-no">No</td>
<td>
</td></tr>
<tr>
<td><a href="/wiki/Maple_(software)" title="Maple (software)">Maple</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#F99;vertical-align:middle;text-align:center;" class="table-no">No</td>
<td>
</td></tr>
<tr>
<td><a href="/wiki/Mathematica" class="mw-redirect" title="Mathematica">Mathematica</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#F99;vertical-align:middle;text-align:center;" class="table-no">No</td>
<td>
</td></tr>
<tr>
<td><a href="/wiki/MATLAB" title="MATLAB">MATLAB</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes<sup id="cite_ref-19" class="reference"><a href="#cite_note-19">[19]</a></sup></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td>Partial application possible by automatic generation of new functions.<sup id="cite_ref-20" class="reference"><a href="#cite_note-20">[20]</a></sup>
</td></tr>
<tr>
<td><a href="/wiki/Smalltalk" title="Smalltalk">Smalltalk</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#FFB;vertical-align:middle;text-align:center;" class="table-partial">Partial</td>
<td>Partial application possible through library.
</td></tr>
<tr>
<td><a href="/wiki/Swift_(programming_language)" title="Swift (programming language)">Swift</a></td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td style="background:#9F9;vertical-align:middle;text-align:center;" class="table-yes">Yes</td>
<td>
</td></tr></tbody></table>
